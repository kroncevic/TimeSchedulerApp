import "./vendor.ts";

import { NgModule, Injector } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { HTTP_INTERCEPTORS } from "@angular/common/http";
import { Ng2Webstorage } from "ngx-webstorage";
import { JhiEventManager } from "ng-jhipster";

import { AuthExpiredInterceptor } from "./blocks/interceptor/auth-expired.interceptor";
import { ErrorHandlerInterceptor } from "./blocks/interceptor/errorhandler.interceptor";
import { NotificationInterceptor } from "./blocks/interceptor/notification.interceptor";
import { TimeSchedulerAppSharedModule } from "app/shared";
import { TimeSchedulerAppCoreModule } from "app/core";
import { TimeSchedulerAppAppRoutingModule } from "./app-routing.module";
import { TimeSchedulerAppHomeModule } from "./home/home.module";
import { TimeSchedulerAppAccountModule } from "./account/account.module";
import { TimeSchedulerAppEntityModule } from "./entities/entity.module";
import { StateStorageService } from "app/core/auth/state-storage.service";

import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { CalendarModule, DateAdapter } from "angular-calendar";
import { adapterFactory } from "angular-calendar/date-adapters/date-fns";

import { NgbdCarouselConfig } from "app/home/carousel.component";

// jhipster-needle-angular-add-module-import JHipster will add new module here
import {
    JhiMainComponent,
    NavbarComponent,
    FooterComponent,
    PageRibbonComponent,
    ErrorComponent
} from "./layouts";

@NgModule({
    imports: [
        BrowserModule,
        TimeSchedulerAppAppRoutingModule,
        Ng2Webstorage.forRoot({ prefix: "jhi", separator: "-" }),
        TimeSchedulerAppSharedModule,
        TimeSchedulerAppCoreModule,
        TimeSchedulerAppHomeModule,
        TimeSchedulerAppAccountModule,
        TimeSchedulerAppEntityModule,
        BrowserAnimationsModule,
        CalendarModule.forRoot({
            provide: DateAdapter,
            useFactory: adapterFactory
        })
        // jhipster-needle-angular-add-module JHipster will add new module here
    ],
    declarations: [
        JhiMainComponent,
        NavbarComponent,
        ErrorComponent,
        PageRibbonComponent,
        FooterComponent,
        NgbdCarouselConfig
    ],
    providers: [
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AuthExpiredInterceptor,
            multi: true,
            deps: [StateStorageService, Injector]
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: ErrorHandlerInterceptor,
            multi: true,
            deps: [JhiEventManager]
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: NotificationInterceptor,
            multi: true,
            deps: [Injector]
        }
    ],
    bootstrap: [JhiMainComponent]
})
export class TimeSchedulerAppAppModule {}
