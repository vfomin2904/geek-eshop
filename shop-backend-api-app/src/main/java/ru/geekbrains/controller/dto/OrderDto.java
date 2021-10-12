package ru.geekbrains.controller.dto;

import ru.geekbrains.service.dto.LineItem;

import java.math.BigDecimal;
import java.util.List;

public class OrderDto {

    private AllCartDto cart;

    private Long userId;

    public OrderDto() {
    }

    public AllCartDto getCart() {
        return cart;
    }

    public void setCart(AllCartDto cart) {
        this.cart = cart;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
