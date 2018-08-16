import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TimeSchedulerAppSharedModule } from 'app/shared';
import { HOME_ROUTE, HomeComponent } from './';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

@NgModule({
    imports: [TimeSchedulerAppSharedModule, RouterModule.forChild([HOME_ROUTE]), NgbModule],
    declarations: [HomeComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TimeSchedulerAppHomeModule {}
