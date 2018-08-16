import { NgModule } from '@angular/core';

import { TimeSchedulerAppSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [TimeSchedulerAppSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [TimeSchedulerAppSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class TimeSchedulerAppSharedCommonModule {}
