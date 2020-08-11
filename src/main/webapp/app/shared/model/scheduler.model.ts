export interface IScheduler {
    id?: number;
    start?: Date;
    end?: Date;
    title?: String;
    numberOfStudents?: number;
    numberOfSubmitted?: number;
}

export class Scheduler implements IScheduler {
    constructor(
        public id?: number,
        public subjectName?: String,
        public startTime?: Date,
        public endTime?: Date,
        public numberOfStudents?: number,
        public numberOfSubmitted?: number
    ) {}
}
