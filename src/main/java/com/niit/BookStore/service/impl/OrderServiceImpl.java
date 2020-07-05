package com.niit.BookStore.service.impl;

import com.niit.BookStore.dto.OrderDto;
import com.niit.BookStore.entiny.Cart;
import com.niit.BookStore.entiny.Item;
import com.niit.BookStore.entiny.Order;
import com.niit.BookStore.entiny.OrderItem;
import com.niit.BookStore.entiny.enums.OrderState;
import com.niit.BookStore.exception.ChangeStateOrderException;
import com.niit.BookStore.exception.ItemNotFoundException;
import com.niit.BookStore.exception.MakeOrderAppException;
import com.niit.BookStore.repository.OrderRepository;
import com.niit.BookStore.service.CustomConversionService;
import com.niit.BookStore.service.OrderService;
import com.niit.BookStore.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private static final String NOT_FOUND_EXCEPTION_MESSAGE = "Order not found";
    private final OrderRepository orderRepository;
    private final CustomConversionService conversionService;
    private final PromoService promoService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            CustomConversionService conversionService,
                            PromoService promoService) {
        this.orderRepository = orderRepository;
        this.conversionService = conversionService;
        this.promoService = promoService;
    }

    @Override
    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE));
        return conversionService.convert(order, OrderDto.class);
    }

    @Override
    public OrderDto cancelOrder(Long id){
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE));

        if(order.getState() == OrderState.CANCELED || order.getState() == OrderState.COMPLETED){
            throw new ChangeStateOrderException(String.format("You cannot cancel order in state: '%s'",
                    order.getState()));
        }

        for (OrderItem orderItem: order.getOrderItems()){
            Item item = orderItem.getItem();
            item.setCount(item.getCount()+orderItem.getCount());
        }

        order.getPerson().setBonus(order.getPerson().getBonus()+order.getUsedBonuses());
        order.setUsedBonuses(0);
        order.setState(OrderState.CANCELED);

        return conversionService.convert(order, OrderDto.class);
    }

    @Override
    public OrderDto completeOrder(Long id){
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ItemNotFoundException(NOT_FOUND_EXCEPTION_MESSAGE));

        if(order.getState() == OrderState.CANCELED || order.getState() == OrderState.COMPLETED){
            throw new ChangeStateOrderException(String.format("You cannot complete order in state: '%s'",
                    order.getState()));
        }

        order.getPerson().setBonus(order.getPerson().getBonus()+order.getEarnedBonuses());

        order.setState(OrderState.COMPLETED);

        return conversionService.convert(order, OrderDto.class);
    }

    @Override
    public OrderDto makeOrder(Cart cart) {
        Order newOrder = Order.builder()
                .person(cart.getPerson())
                .address(cart.getPerson().getAddress())
                .placedOn(LocalDateTime.now())
                .earnedBonuses(0)
                .usedBonuses(0)
                .build();
        //Create OrderItems object to make the order
        BigDecimal orderPrice = new BigDecimal(0);
        Set<OrderItem> orderItemSet = new HashSet<>();
        for (Item item : cart.getItems()) {
            OrderItem orderItem = OrderItem.builder()
                    .order(newOrder)
                    .item(item)
                    .count(1)
                    .build();

            if(item.getCount()-1 < 0){
                throw new MakeOrderAppException(String.format("Sorry, there isn't enough '%s' items in stock", item.getName()));
            }
            item.setCount(item.getCount()-1);

            //Get Item price with discount
            orderItem.setPrice(promoService.getItemPriseWithDiscount(cart.getPromo(), item, cart.getPerson()));

            orderPrice = orderPrice.add(orderItem.getPrice());
            orderItemSet.add(orderItem);
        }
        newOrder.setOrderItems(orderItemSet);
        //Set total price
        newOrder.setTotal(orderPrice);

        //Try to apply bonuses from cart
        Integer cartBonuses = cart.getAppliedBonuses();
        if(Objects.nonNull(cartBonuses) && cartBonuses > 0){
            Integer personBonuses = cart.getPerson().getBonus();
            Integer minAvailableBonuses = Collections.min(
                    new ArrayList<>(
                            Arrays.asList(cartBonuses, newOrder.getTotal().intValue(), personBonuses)));
            newOrder.setUsedBonuses(minAvailableBonuses);
            cart.getPerson().setBonus(personBonuses-minAvailableBonuses);
        }
        // Calculate new bonuses for order
        // TODO: Now '%' of bonuses is hardcoded  and equal to 5%. try to find where is better to store this value
        Integer earnedBonuses = newOrder.getTotal()
                .subtract(new BigDecimal(newOrder.getUsedBonuses()))
                .multiply(new BigDecimal(5))
                .divide(new BigDecimal(100), RoundingMode.UP).intValue();
        newOrder.setEarnedBonuses(earnedBonuses);

        orderRepository.save(newOrder);
        return conversionService.convert(newOrder, OrderDto.class);
    }

    @Override
    public List<OrderDto> getAll() {
        List<Order> orders = orderRepository.findAll();
        return orders
                .stream()
                .map((Order order) -> conversionService.convert(order, OrderDto.class))
                .collect(Collectors.toList());
    }

}
