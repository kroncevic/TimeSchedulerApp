import { NgModule, CUSTOM_ELEMENTS_SCHEMA, LOCALE_ID } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { FlatpickrModule } from 'angularx-flatpickr';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { RouterModule } from '@angular/router';
import { TimeSchedulerAppSharedModule } from 'app/shared';
import { CustomDatePipe } from 'app/shared/custom.datepipe';
import {
    SchedulerComponent,
    schedulerRoute,
    studentRoute,
    schedulerPopupRoute,
    studentPopupRoute,
    SchedulerUpdateComponent,
    SchedulerDeletePopupComponent,
    SchedulerDeleteDialogComponent,
    StudentDeletePopupComponent,
    StudentDeleteDialogComponent,
    SchedulerDetailComponent,
    SchedulerStudentViewComponent,
    SchedulerReservationComponent
} from './';

const ENTITY_STATES = [
    ...schedulerRoute,
    ...schedulerPopupRoute,
    ...studentRoute,
    ...studentPopupRoute
];

@NgModule({
    imports: [
        TimeSchedulerAppSharedModule,
        RouterModule.forChild(ENTITY_STATES),
        CommonModule,
        FormsModule,
        NgbModalModule,
        FlatpickrModule.forRoot(),
        CalendarModule.forRoot({
            provide: DateAdapter,
            useFactory: adapterFactory
        })
    ],
    declarations: [
        SchedulerComponent,
        SchedulerUpdateComponent,
        SchedulerDeletePopupComponent,
        SchedulerDeleteDialogComponent,
        StudentDeletePopupComponent,
        StudentDeleteDialogComponent,
        SchedulerDetailComponent,
        SchedulerStudentViewComponent,
        SchedulerReservationComponent,
        CustomDatePipe
    ],
    exports: [SchedulerComponent],
    entryComponents: [
        SchedulerComponent,
        SchedulerUpdateComponent,
        SchedulerDeletePopupComponent,
        SchedulerDeleteDialogComponent,
        StudentDeletePopupComponent,
        StudentDeleteDialogComponent,
        SchedulerStudentViewComponent,
        SchedulerReservationComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    providers: [
        {
            provide: LOCALE_ID,
            useValue: 'hr'
        }
    ]
})
export class TimeSchedulerCalendarAppModule {}
