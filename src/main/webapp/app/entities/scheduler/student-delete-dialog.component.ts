import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import {
    NgbActiveModal,
    NgbModal,
    NgbModalRef
} from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IStudent } from 'app/shared/model/student.model';
import { IScheduler } from 'app/shared/model/scheduler.model';
import { StudentService } from './student.service';

@Component({
    selector: 'jhi-student-delete-dialog',
    templateUrl: './student-delete-dialog.component.html'
})
export class StudentDeleteDialogComponent implements OnInit {
    student: IStudent;
    scheduler: IScheduler;
    schedulerId: number;

    constructor(
        private studentService: StudentService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager,
        private activatedRoute: ActivatedRoute,
    ) {}

    ngOnInit() {
        this.activatedRoute.firstChild.data.subscribe(({ schedule }) => {
            this.scheduler = schedule;
        });
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.studentService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'studentListModification',
                content: 'Student deleted'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-student-delete-popup',
    template: ''
})
export class StudentDeletePopupComponent implements OnInit, OnDestroy {
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
                    StudentDeleteDialogComponent as Component,
                    { size: 'lg', backdrop: 'static' }
                );
                this.ngbModalRef.componentInstance.student = schedule;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], {
                            replaceUrl: true,
                            queryParamsHandling: 'merge'
                        });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], {
                            replaceUrl: true,
                            queryParamsHandling: 'merge'
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
