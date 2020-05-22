import { Component, OnInit, OnDestroy } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";

import {
    NgbActiveModal,
    NgbModal,
    NgbModalRef
} from "@ng-bootstrap/ng-bootstrap";
import { JhiEventManager } from "ng-jhipster";

import { IScheduler } from "app/shared/model/scheduler.model";
import { SchedulerService } from "./scheduler.service";

@Component({
    selector: "jhi-scheduler-delete-dialog",
    templateUrl: "./scheduler-delete-dialog.component.html"
})
export class SchedulerDeleteDialogComponent {
    schedule: IScheduler;

    constructor(
        private schedulerService: SchedulerService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss("cancel");
    }

    confirmDelete(id: number) {
        this.schedulerService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: "schedulerListModification",
                content: "Deleted a schedule"
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: "jhi-scheduler-delete-popup",
    template: ""
})
export class SchedulerDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private modalService: NgbModal
    ) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ schedule }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(
                    SchedulerDeleteDialogComponent as Component,
                    { size: "lg", backdrop: "static" }
                );
                this.ngbModalRef.componentInstance.schedule = schedule;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], {
                            replaceUrl: true,
                            queryParamsHandling: "merge"
                        });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], {
                            replaceUrl: true,
                            queryParamsHandling: "merge"
                        });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
