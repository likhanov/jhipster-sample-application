import { IOrder } from 'app/shared/model/order.model';
import { StatusRequest } from 'app/shared/model/enumerations/status-request.model';

export interface IRequest {
  id?: string;
  requestID?: string;
  status?: StatusRequest;
  error?: string;
  orders?: IOrder[];
}

export class Request implements IRequest {
  constructor(
    public id?: string,
    public requestID?: string,
    public status?: StatusRequest,
    public error?: string,
    public orders?: IOrder[]
  ) {}
}
