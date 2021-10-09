import {Component, OnInit} from '@angular/core';
import {CartService} from "../../services/cart.service";
import {AllCartDto} from "../../model/all-cart-dto";
import {AddLineItemDto} from "../../model/add-line-item-dto";
import {LineItem} from "../../model/line-item";
import {QtyCartDto} from "../../model/qty-cart-dto";

export const CART_URL = 'cart'

@Component({
  selector: 'app-cart-page',
  templateUrl: './cart-page.component.html',
  styleUrls: ['./cart-page.component.scss']
})
export class CartPageComponent implements OnInit {

  content?: AllCartDto;
  constructor(private cartService: CartService) {
  }

  ngOnInit(): void {
    this.cartService.findAll().subscribe(
      res => {
        this.content = res;
      }
    )
  }

  save(lineItem: LineItem, count: number){
    if(lineItem.qty > count){
      this.cartService.removeQty(new QtyCartDto(lineItem, lineItem.qty - count)).subscribe(
        res => {
          this.content = res;
        }
      );
    } else if (lineItem.qty < count){
      this.cartService.addQty(new QtyCartDto(lineItem, count - lineItem.qty)).subscribe(
        res => {
          this.content = res;
        }
      );
    }
  }

  delete(lineItem:LineItem){
    this.cartService.delete(lineItem).subscribe(
      res => {
        this.content = res;
      }
    );
  }

  clear() {
    this.cartService.clear().subscribe(
      res => {
        this.content = res;
      }
    );
  }

}
