import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AddLineItemDto} from "../model/add-line-item-dto";
import {Observable} from "rxjs";
import {AllCartDto} from "../model/all-cart-dto";
import {LineItem} from "../model/line-item";
import {QtyCartDto} from "../model/qty-cart-dto";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) { }

  public findAll() : Observable<AllCartDto> {
    return this.http.get<AllCartDto>('/api/v1/cart/all');
  }

  public addToCart(dto: AddLineItemDto) {
    return this.http.post('/api/v1/cart', dto)
  }

  public delete(dto: LineItem) {
    return this.http.put<AllCartDto>('/api/v1/cart/delete', dto)
  }

  public clear() {
    return this.http.delete<AllCartDto>('/api/v1/cart/clear')
  }

  public addQty(qtyCartDto: QtyCartDto) {
    return this.http.put<AllCartDto>('/api/v1/cart/addQty', qtyCartDto)
  }

  public removeQty(qtyCartDto: QtyCartDto) {
    return this.http.put<AllCartDto>('/api/v1/cart/removeQty', qtyCartDto)
  }
}
