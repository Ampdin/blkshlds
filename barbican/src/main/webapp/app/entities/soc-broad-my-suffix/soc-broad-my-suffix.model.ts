import { BaseEntity } from './../../shared';

export class SocBroadMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public socBroadName?: string,
        public socBroadCode?: string,
        public socBroadAvatorContentType?: string,
        public socBroadAvator?: any,
        public socBroadDescription?: string,
        public socBroadURL?: string,
        public socBroadPreviewImage?: string,
        public socMinorId?: number,
        public socspecifics?: BaseEntity[],
    ) {
    }
}
