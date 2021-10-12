package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.controller.dto.OrderDto;
import ru.geekbrains.persist.*;
import ru.geekbrains.persist.model.LineItem;
import ru.geekbrains.persist.model.Order;
import ru.geekbrains.persist.model.Product;
import ru.geekbrains.persist.model.User;
import ru.geekbrains.service.CartServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartServiceImpl cartService;
    private final ProductRepository productRepository;

    public OrderController(OrderRepository orderRepository, UserRepository userRepository, CartServiceImpl cartService, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cartService = cartService;
        this.productRepository = productRepository;
    }

    @GetMapping("/all")
    public Authentication findAll(Authentication auth) {
        return auth;
    }

    @GetMapping("/{orderId}")
    public Order findByUser(@PathVariable Long orderId) {
        return orderRepository.findById(orderId).get();
    }

    @PostMapping("/create")
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        Order order = new Order();
        User user = userRepository.findById(orderDto.getUserId()).get();
        order.setUser(user);
        Set<LineItem> lineItems = new HashSet<>();
        for(ru.geekbrains.service.dto.LineItem lineItemCart: cartService.getLineItems()){
            Product product = productRepository.findById(lineItemCart.getProductId()).get();
            LineItem lineItem = new LineItem(product, lineItemCart.getColor(), lineItemCart.getMaterial(), lineItemCart.getQty());
            lineItems.add(lineItem);
        }
        if(lineItems.isEmpty()){
            return null;
        }
        order.setLineItems(lineItems);
        orderRepository.save(order);
        return orderDto;
    }
}
