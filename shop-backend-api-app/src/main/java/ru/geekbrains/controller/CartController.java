package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.controller.dto.AddLineItemDto;
import ru.geekbrains.controller.dto.AllCartDto;
import ru.geekbrains.controller.dto.ProductDto;
import ru.geekbrains.controller.dto.ChangeQtyLineItemDto;
import ru.geekbrains.service.CartService;
import ru.geekbrains.service.ProductService;
import ru.geekbrains.service.dto.LineItem;

import java.util.List;

@RequestMapping("/cart")
@RestController
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    private final CartService cartService;

    private final ProductService productService;

    @Autowired
    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public List<LineItem> addToCart(@RequestBody AddLineItemDto addLineItemDto) {
        logger.info("New LineItem. ProductId = {}, qty = {}", addLineItemDto.getProductId(), addLineItemDto.getQty());

        ProductDto productDto = productService.findById(addLineItemDto.getProductId())
                .orElseThrow(RuntimeException::new);
        cartService.addProductQty(productDto, addLineItemDto.getColor(), addLineItemDto.getMaterial(), addLineItemDto.getQty());
        return cartService.getLineItems();
    }

    @PutMapping("/delete")
    public List<LineItem> deleteFromCart(@RequestBody AddLineItemDto addLineItemDto) {
        logger.info("Delete LineItem. ProductId = {}", addLineItemDto.getProductId());

        ProductDto productDto = productService.findById(addLineItemDto.getProductId())
                .orElseThrow(RuntimeException::new);
        cartService.removeProduct(productDto, addLineItemDto.getColor(), addLineItemDto.getMaterial());
        return cartService.getLineItems();
    }

    @DeleteMapping("/clear")
    public List<LineItem> clearCart() {
        logger.info("Clear cart");
        cartService.clear();
        return cartService.getLineItems();
    }

    @PutMapping("/removeQty")
    public List<LineItem> removeProductQty(@RequestBody ChangeQtyLineItemDto lineItemDto) {
        logger.info("Remove qty LineItem. ProductId = {}",  lineItemDto.getLineItem().getProductId());

        ProductDto productDto = productService.findById( lineItemDto.getLineItem().getProductId())
                .orElseThrow(RuntimeException::new);
        cartService.removeProductQty(productDto,  lineItemDto.getLineItem().getColor(),  lineItemDto.getLineItem().getMaterial(), lineItemDto.getCount());
        return cartService.getLineItems();
    }

    @PutMapping("/addQty")
    public List<LineItem> addProductQty(@RequestBody ChangeQtyLineItemDto lineItemDto) {
        logger.info("Add qty LineItem. ProductId = {}", lineItemDto.getLineItem().getProductId());

        ProductDto productDto = productService.findById(lineItemDto.getLineItem().getProductId())
                .orElseThrow(RuntimeException::new);
        cartService.addProductQty(productDto, lineItemDto.getLineItem().getColor(), lineItemDto.getLineItem().getMaterial(), lineItemDto.getCount());
        return cartService.getLineItems();
    }

    @GetMapping("/all")
    public AllCartDto findAll() {
        return new AllCartDto(cartService.getLineItems(), cartService.getSubTotal());
    }
}
