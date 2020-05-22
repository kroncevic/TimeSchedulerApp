export interface ISubject {
    id?: number;
    name?: string;
    description?: string;
}

export class Subject implements ISubject {
    constructor(
        public id?: number,
        public subjectName?: string,
        public subjectDescription?: string
    ) {}
}
