export interface IStudent {
    id?: number;
    schedule?: Object;
    name?: String;
    lastName?: String;
    phone?: String;
}

export class Student implements IStudent {
    constructor(
        public id?: number,
        public schedule?: Object,
        public studentName?: String,
        public studentLastName?: String,
        public phoneNumber?: String
    ) {}
}
