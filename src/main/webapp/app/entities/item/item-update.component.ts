import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IItem, Item } from 'app/shared/model/item.model';
import { ItemService } from './item.service';
import { IOrder } from 'app/shared/model/order.model';
import { OrderService } from 'app/entities/order/order.service';

@Component({
  selector: 'jhi-item-update',
  templateUrl: './item-update.component.html'
})
export class ItemUpdateComponent implements OnInit {
  isSaving = false;
  orders: IOrder[] = [];

  editForm = this.fb.group({
    id: [],
    pPOID: [null, [Validators.required]],
    labelID: [],
    tDCode: [],
    quantity: [],
    salesCountryName: [],
    comment: [],
    stickerID: [],
    order: []
  });

  constructor(
    protected itemService: ItemService,
    protected orderService: OrderService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ item }) => {
      this.updateForm(item);

      this.orderService.query().subscribe((res: HttpResponse<IOrder[]>) => (this.orders = res.body || []));
    });
  }

  updateForm(item: IItem): void {
    this.editForm.patchValue({
      id: item.id,
      pPOID: item.pPOID,
      labelID: item.labelID,
      tDCode: item.tDCode,
      quantity: item.quantity,
      salesCountryName: item.salesCountryName,
      comment: item.comment,
      stickerID: item.stickerID,
      order: item.order
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const item = this.createFromForm();
    if (item.id !== undefined) {
      this.subscribeToSaveResponse(this.itemService.update(item));
    } else {
      this.subscribeToSaveResponse(this.itemService.create(item));
    }
  }

  private createFromForm(): IItem {
    return {
      ...new Item(),
      id: this.editForm.get(['id'])!.value,
      pPOID: this.editForm.get(['pPOID'])!.value,
      labelID: this.editForm.get(['labelID'])!.value,
      tDCode: this.editForm.get(['tDCode'])!.value,
      quantity: this.editForm.get(['quantity'])!.value,
      salesCountryName: this.editForm.get(['salesCountryName'])!.value,
      comment: this.editForm.get(['comment'])!.value,
      stickerID: this.editForm.get(['stickerID'])!.value,
      order: this.editForm.get(['order'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IItem>>): void {
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

  trackById(index: number, item: IOrder): any {
    return item.id;
  }
}
