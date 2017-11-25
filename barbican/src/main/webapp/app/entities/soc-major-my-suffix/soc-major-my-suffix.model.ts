import { BaseEntity } from './../../shared';

export class SocMajorMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public socMajorName?: string,
        public socMajorCode?: string,
        public socMajorAvatorContentType?: string,
        public socMajorAvator?: any,
        public socMajorDescription?: string,
        public socMajorURL?: string,
        public socMajorPreviewImage?: string,
        public socminors?: BaseEntity[],
    ) {
    }
}
