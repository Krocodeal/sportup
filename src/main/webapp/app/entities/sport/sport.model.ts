import { BaseEntity } from './../../shared';

export class Sport implements BaseEntity {
    constructor(
        public id?: number,
        public nom?: string,
        public imageContentType?: string,
        public image?: any,
        public toMuscler?: boolean,
        public toSecher?: boolean,
        public isCollectif?: boolean,
        public toCibleHaut?: boolean,
        public toCibleBas?: boolean,
        public isContact?: boolean,
        public isBalle?: boolean,
        public isIntense?: boolean,
        public isCombat?: boolean,
        public isPercussion?: boolean,
        public isCher?: boolean,
        public isArtistique?: boolean,
    ) {
        this.toMuscler = false;
        this.toSecher = false;
        this.isCollectif = false;
        this.toCibleHaut = false;
        this.toCibleBas = false;
        this.isContact = false;
        this.isBalle = false;
        this.isIntense = false;
        this.isCombat = false;
        this.isPercussion = false;
        this.isCher = false;
        this.isArtistique = false;
    }
}
