/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { BarbicanTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { SocBroadMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/soc-broad-my-suffix/soc-broad-my-suffix-detail.component';
import { SocBroadMySuffixService } from '../../../../../../main/webapp/app/entities/soc-broad-my-suffix/soc-broad-my-suffix.service';
import { SocBroadMySuffix } from '../../../../../../main/webapp/app/entities/soc-broad-my-suffix/soc-broad-my-suffix.model';

describe('Component Tests', () => {

    describe('SocBroadMySuffix Management Detail Component', () => {
        let comp: SocBroadMySuffixDetailComponent;
        let fixture: ComponentFixture<SocBroadMySuffixDetailComponent>;
        let service: SocBroadMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [BarbicanTestModule],
                declarations: [SocBroadMySuffixDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    SocBroadMySuffixService,
                    JhiEventManager
                ]
            }).overrideTemplate(SocBroadMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SocBroadMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SocBroadMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new SocBroadMySuffix(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.socBroad).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
