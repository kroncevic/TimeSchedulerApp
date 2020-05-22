import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";

import { TimeSchedulerAppModule } from "./subject/subject.module";
import { TimeSchedulerCalendarAppModule } from "./scheduler/scheduler.module";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { CalendarModule } from "angular-calendar";

/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
		TimeSchedulerAppModule,
		TimeSchedulerCalendarAppModule,
		CommonModule,
		FormsModule,
		CalendarModule
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    exports: [TimeSchedulerAppModule, TimeSchedulerCalendarAppModule],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TimeSchedulerAppEntityModule {}
