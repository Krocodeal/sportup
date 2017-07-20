import { BaseEntity } from './../../shared';

export class Reponse implements BaseEntity {
    constructor(
        public id?: number,
        public libelle?: string,
        public question?: BaseEntity,
    ) {
    }
}
