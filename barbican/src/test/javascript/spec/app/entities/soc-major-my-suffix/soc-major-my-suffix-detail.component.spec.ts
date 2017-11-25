/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { BarbicanTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { SocMajorMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/soc-major-my-suffix/soc-major-my-suffix-detail.component';
import { SocMajorMySuffixService } from '../../../../../../main/webapp/app/entities/soc-major-my-suffix/soc-major-my-suffix.service';
import { SocMajorMySuffix } from '../../../../../../main/webapp/app/entities/soc-major-my-suffix/soc-major-my-suffix.model';

describe('Component Tests', () => {

    describe('SocMajorMySuffix Management Detail Component', () => {
        let comp: SocMajorMySuffixDetailComponent;
        let fixture: ComponentFixture<SocMajorMySuffixDetailComponent>;
        let service: SocMajorMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [BarbicanTestModule],
                declarations: [SocMajorMySuffixDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    SocMajorMySuffixService,
                    JhiEventManager
                ]
            }).overrideTemplate(SocMajorMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SocMajorMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SocMajorMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new SocMajorMySuffix(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.socMajor).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
