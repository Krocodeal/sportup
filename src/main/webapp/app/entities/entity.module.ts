import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { SportupSportModule } from './sport/sport.module';
import { SportupQuestionModule } from './question/question.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        SportupSportModule,
        SportupQuestionModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SportupEntityModule {}
