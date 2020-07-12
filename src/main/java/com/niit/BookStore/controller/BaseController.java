package com.niit.BookStore.controller;

import com.niit.BookStore.security.StoreUser;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseController {
    protected Long getUserIdFromSecurityContext(){
        StoreUser user = (StoreUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUserId();
    }
}
