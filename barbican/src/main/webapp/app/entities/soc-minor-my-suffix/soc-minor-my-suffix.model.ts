import { BaseEntity } from './../../shared';

export class SocMinorMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public socMinorName?: string,
        public socMinorCode?: string,
        public socMinorAvatorContentType?: string,
        public socMinorAvator?: any,
        public socMinorDescription?: string,
        public socMinorURL?: string,
        public socMinorPreviewImage?: string,
        public socMajorId?: number,
        public socbroads?: BaseEntity[],
    ) {
    }
}
