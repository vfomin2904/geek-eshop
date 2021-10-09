import {LineItem} from "./line-item";

export class QtyCartDto {

  constructor(public lineItem: LineItem,
              public count: number) {
  }
}
