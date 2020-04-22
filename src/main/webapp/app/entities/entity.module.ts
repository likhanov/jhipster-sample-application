import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'request',
        loadChildren: () => import('./request/request.module').then(m => m.JhipsterSampleApplicationRequestModule)
      },
      {
        path: 'order',
        loadChildren: () => import('./order/order.module').then(m => m.JhipsterSampleApplicationOrderModule)
      },
      {
        path: 'item',
        loadChildren: () => import('./item/item.module').then(m => m.JhipsterSampleApplicationItemModule)
      },
      {
        path: 'model',
        loadChildren: () => import('./model/model.module').then(m => m.JhipsterSampleApplicationModelModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class JhipsterSampleApplicationEntityModule {}
