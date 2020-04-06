import { IItem } from 'app/shared/model/item.model';
import { IRequest } from 'app/shared/model/request.model';

export interface IOrder {
  id?: string;
  orderID?: string;
  version?: number;
  collectionName?: string;
  isCancelled?: boolean;
  nomineeName?: string;
  vendorName?: string;
  factoryName?: string;
  deliveryAddress?: string;
  comment?: string;
  cILQuantity?: number;
  cILRequestQuantity?: number;
  items?: IItem[];
  request?: IRequest;
}

export class Order implements IOrder {
  constructor(
    public id?: string,
    public orderID?: string,
    public version?: number,
    public collectionName?: string,
    public isCancelled?: boolean,
    public nomineeName?: string,
    public vendorName?: string,
    public factoryName?: string,
    public deliveryAddress?: string,
    public comment?: string,
    public cILQuantity?: number,
    public cILRequestQuantity?: number,
    public items?: IItem[],
    public request?: IRequest
  ) {
    this.isCancelled = this.isCancelled || false;
  }
}
