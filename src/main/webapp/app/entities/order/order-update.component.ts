import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IOrder, Order } from 'app/shared/model/order.model';
import { OrderService } from './order.service';
import { IRequest } from 'app/shared/model/request.model';
import { RequestService } from 'app/entities/request/request.service';

@Component({
  selector: 'jhi-order-update',
  templateUrl: './order-update.component.html'
})
export class OrderUpdateComponent implements OnInit {
  isSaving = false;
  requests: IRequest[] = [];

  editForm = this.fb.group({
    id: [],
    orderID: [null, [Validators.required]],
    version: [],
    collectionName: [],
    isCancelled: [],
    nomineeName: [],
    vendorName: [],
    factoryName: [],
    deliveryAddress: [],
    comment: [],
    cILQuantity: [],
    cILRequestQuantity: [],
    request: []
  });

  constructor(
    protected orderService: OrderService,
    protected requestService: RequestService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ order }) => {
      this.updateForm(order);

      this.requestService.query().subscribe((res: HttpResponse<IRequest[]>) => (this.requests = res.body || []));
    });
  }

  updateForm(order: IOrder): void {
    this.editForm.patchValue({
      id: order.id,
      orderID: order.orderID,
      version: order.version,
      collectionName: order.collectionName,
      isCancelled: order.isCancelled,
      nomineeName: order.nomineeName,
      vendorName: order.vendorName,
      factoryName: order.factoryName,
      deliveryAddress: order.deliveryAddress,
      comment: order.comment,
      cILQuantity: order.cILQuantity,
      cILRequestQuantity: order.cILRequestQuantity,
      request: order.request
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const order = this.createFromForm();
    if (order.id !== undefined) {
      this.subscribeToSaveResponse(this.orderService.update(order));
    } else {
      this.subscribeToSaveResponse(this.orderService.create(order));
    }
  }

  private createFromForm(): IOrder {
    return {
      ...new Order(),
      id: this.editForm.get(['id'])!.value,
      orderID: this.editForm.get(['orderID'])!.value,
      version: this.editForm.get(['version'])!.value,
      collectionName: this.editForm.get(['collectionName'])!.value,
      isCancelled: this.editForm.get(['isCancelled'])!.value,
      nomineeName: this.editForm.get(['nomineeName'])!.value,
      vendorName: this.editForm.get(['vendorName'])!.value,
      factoryName: this.editForm.get(['factoryName'])!.value,
      deliveryAddress: this.editForm.get(['deliveryAddress'])!.value,
      comment: this.editForm.get(['comment'])!.value,
      cILQuantity: this.editForm.get(['cILQuantity'])!.value,
      cILRequestQuantity: this.editForm.get(['cILRequestQuantity'])!.value,
      request: this.editForm.get(['request'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrder>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IRequest): any {
    return item.id;
  }
}
