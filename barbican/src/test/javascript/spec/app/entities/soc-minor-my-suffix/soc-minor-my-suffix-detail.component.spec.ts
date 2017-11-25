/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { BarbicanTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { SocMinorMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/soc-minor-my-suffix/soc-minor-my-suffix-detail.component';
import { SocMinorMySuffixService } from '../../../../../../main/webapp/app/entities/soc-minor-my-suffix/soc-minor-my-suffix.service';
import { SocMinorMySuffix } from '../../../../../../main/webapp/app/entities/soc-minor-my-suffix/soc-minor-my-suffix.model';

describe('Component Tests', () => {

    describe('SocMinorMySuffix Management Detail Component', () => {
        let comp: SocMinorMySuffixDetailComponent;
        let fixture: ComponentFixture<SocMinorMySuffixDetailComponent>;
        let service: SocMinorMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [BarbicanTestModule],
                declarations: [SocMinorMySuffixDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    SocMinorMySuffixService,
                    JhiEventManager
                ]
            }).overrideTemplate(SocMinorMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SocMinorMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SocMinorMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new SocMinorMySuffix(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.socMinor).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
