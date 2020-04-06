import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRequest, Request } from 'app/shared/model/request.model';
import { RequestService } from './request.service';

@Component({
  selector: 'jhi-request-update',
  templateUrl: './request-update.component.html'
})
export class RequestUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    requestID: [null, [Validators.required]],
    status: [],
    error: []
  });

  constructor(protected requestService: RequestService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ request }) => {
      this.updateForm(request);
    });
  }

  updateForm(request: IRequest): void {
    this.editForm.patchValue({
      id: request.id,
      requestID: request.requestID,
      status: request.status,
      error: request.error
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const request = this.createFromForm();
    if (request.id !== undefined) {
      this.subscribeToSaveResponse(this.requestService.update(request));
    } else {
      this.subscribeToSaveResponse(this.requestService.create(request));
    }
  }

  private createFromForm(): IRequest {
    return {
      ...new Request(),
      id: this.editForm.get(['id'])!.value,
      requestID: this.editForm.get(['requestID'])!.value,
      status: this.editForm.get(['status'])!.value,
      error: this.editForm.get(['error'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRequest>>): void {
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
}
