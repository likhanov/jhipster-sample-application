import { IOrder } from 'app/shared/model/order.model';

export interface IItem {
  id?: string;
  pPOID?: string;
  labelID?: string;
  tDCode?: string;
  quantity?: number;
  salesCountryName?: string;
  comment?: string;
  stickerID?: string;
  order?: IOrder;
}

export class Item implements IItem {
  constructor(
    public id?: string,
    public pPOID?: string,
    public labelID?: string,
    public tDCode?: string,
    public quantity?: number,
    public salesCountryName?: string,
    public comment?: string,
    public stickerID?: string,
    public order?: IOrder
  ) {}
}
