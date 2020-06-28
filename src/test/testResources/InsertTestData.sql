USE book_store;

INSERT INTO address (country, city, street, building, apartment, info, postal_code)
VALUES ('Russia', 'N.Novgorod', 'Udmurdskaya', '1', '1', 'Please call before came', '603000'),
       ('Russia', 'N.Novgorod', 'Rozdestvenskaya', '2', '1', 'Please bring Items only after 12:00 am', '603000'),
       ('USA', 'Houston', 'Main', '5', '2', 'We have a problem', '77018');

INSERT into person (first_name, last_name, email)
VALUES
       ('first_name0', 'last_name0', 'last_name0@mail.com'),
       ('first_name1', 'last_name1', 'last_name1@mail.com'),
       ('first_name2', 'last_name2', 'last_name2@mail.com'),
       ('first_name3', 'last_name3', 'last_name3@mail.com');


INSERT INTO item (name, price, description, count)
VALUES ('NewItem', 100.00, 'Some very important description', 2),
       ('NewItem2', 100.00, 'Some very important description2', 5),
       ('The best NewItem2', 100.00, 'Some very important description5', 10),
       ('Telephone S1', 100.00, 'Some very important description5', 10),
       ('Telephone S2', 200.00, 'Some very important description5', 9),
       ('Telephone S3', 300.00, 'Some very important description5', 8),
       ('Telephone S4', 400.00, 'Some very important description5', 7);

INSERT INTO `order` (person_id, address_id, state, total, placed_on)
VALUES (1, 1, 'PLACED', 500, UTC_TIMESTAMP()),
       (1, 2, 'COMPLETED', 0,  UTC_TIMESTAMP()),
       (3, 3, 'CANCELED', 0,  UTC_TIMESTAMP());

INSERT INTO order_item (order_id, item_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (3, 2),
       (2,1);

INSERT INTO cart (person_id)
VALUES (1),(2),(3),(4);

INSERT INTO cart_item (cart_id, item_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (3, 2),
       (2, 1);

INSERT INTO promo (code, start_date, end_date, discount, type)
VALUES ('MayDay', DATE_SUB(NOW(), INTERVAL 1 MONTH ), DATE_ADD(NOW(), INTERVAL 1 MONTH ), 2, 'COMMON'),
       ('MegaSail',DATE_SUB(NOW(), INTERVAL 1 MONTH ), DATE_ADD(NOW(), INTERVAL 1 MONTH ), 5, 'COMMON'),
       ('t_oidar', DATE_SUB(NOW(), INTERVAL 1 MONTH ), DATE_ADD(NOW(), INTERVAL 1 MONTH ), 10, 'COMMON');

INSERT INTO promo (code, start_date, end_date, discount, type, person_id)
VALUES ('RandomGenerated1', DATE_SUB(NOW(), INTERVAL 1 MONTH ), DATE_ADD(NOW(), INTERVAL 1 MONTH ), 2, 'PERSONAL', 1),
       ('RandomGenerated2',DATE_SUB(NOW(), INTERVAL 1 MONTH ), DATE_ADD(NOW(), INTERVAL 1 MONTH ), 5, 'PERSONAL', 2);

INSERT INTO category(name)
VALUES ('Detective'),
       ('Documentary'),
       ('Fantasy'),
       ('Adventure'),
       ('Romance');

INSERT INTO item_category( item_id, category_id)
VALUES (1,1),
       (1,2),
       (2,4),
       (2,5);


INSERT INTO wish_list (person_id, item_id)
VALUES (2, 4),
       (1, 2),
       (1, 3);

select * from promo;




