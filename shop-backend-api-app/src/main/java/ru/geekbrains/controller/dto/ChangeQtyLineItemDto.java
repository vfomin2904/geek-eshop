package ru.geekbrains.controller.dto;

public class ChangeQtyLineItemDto {

    private AddLineItemDto lineItem;

    private Integer count;

    public ChangeQtyLineItemDto() {
    }

    public AddLineItemDto getLineItem() {
        return lineItem;
    }

    public void setLineItem(AddLineItemDto lineItem) {
        this.lineItem = lineItem;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
