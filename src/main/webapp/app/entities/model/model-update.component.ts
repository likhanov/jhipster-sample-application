import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IModel, Model } from 'app/shared/model/model.model';
import { ModelService } from './model.service';

@Component({
  selector: 'jhi-model-update',
  templateUrl: './model-update.component.html'
})
export class ModelUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    lastName: [null, [Validators.required]],
    firstName: [null, [Validators.required]],
    patronymic: [],
    height: [null, [Validators.required]],
    chest: [null, [Validators.required]],
    waist: [null, [Validators.required]],
    hips: [null, [Validators.required]]
  });

  constructor(protected modelService: ModelService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ model }) => {
      this.updateForm(model);
    });
  }

  updateForm(model: IModel): void {
    this.editForm.patchValue({
      id: model.id,
      lastName: model.lastName,
      firstName: model.firstName,
      patronymic: model.patronymic,
      height: model.height,
      chest: model.chest,
      waist: model.waist,
      hips: model.hips
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const model = this.createFromForm();
    if (model.id !== undefined) {
      this.subscribeToSaveResponse(this.modelService.update(model));
    } else {
      this.subscribeToSaveResponse(this.modelService.create(model));
    }
  }

  private createFromForm(): IModel {
    return {
      ...new Model(),
      id: this.editForm.get(['id'])!.value,
      lastName: this.editForm.get(['lastName'])!.value,
      firstName: this.editForm.get(['firstName'])!.value,
      patronymic: this.editForm.get(['patronymic'])!.value,
      height: this.editForm.get(['height'])!.value,
      chest: this.editForm.get(['chest'])!.value,
      waist: this.editForm.get(['waist'])!.value,
      hips: this.editForm.get(['hips'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IModel>>): void {
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
