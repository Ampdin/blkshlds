import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('SocMajor e2e test', () => {

    let navBarPage: NavBarPage;
    let socMajorDialogPage: SocMajorDialogPage;
    let socMajorComponentsPage: SocMajorComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load SocMajors', () => {
        navBarPage.goToEntity('soc-major-my-suffix');
        socMajorComponentsPage = new SocMajorComponentsPage();
        expect(socMajorComponentsPage.getTitle()).toMatch(/barbicanApp.socMajor.home.title/);

    });

    it('should load create SocMajor dialog', () => {
        socMajorComponentsPage.clickOnCreateButton();
        socMajorDialogPage = new SocMajorDialogPage();
        expect(socMajorDialogPage.getModalTitle()).toMatch(/barbicanApp.socMajor.home.createOrEditLabel/);
        socMajorDialogPage.close();
    });

    it('should create and save SocMajors', () => {
        socMajorComponentsPage.clickOnCreateButton();
        socMajorDialogPage.setSocMajorNameInput('socMajorName');
        expect(socMajorDialogPage.getSocMajorNameInput()).toMatch('socMajorName');
        socMajorDialogPage.setSocMajorCodeInput('socMajorCode');
        expect(socMajorDialogPage.getSocMajorCodeInput()).toMatch('socMajorCode');
        socMajorDialogPage.setSocMajorAvatorInput(absolutePath);
        socMajorDialogPage.setSocMajorDescriptionInput('socMajorDescription');
        expect(socMajorDialogPage.getSocMajorDescriptionInput()).toMatch('socMajorDescription');
        socMajorDialogPage.setSocMajorURLInput('socMajorURL');
        expect(socMajorDialogPage.getSocMajorURLInput()).toMatch('socMajorURL');
        socMajorDialogPage.setSocMajorPreviewImageInput('socMajorPreviewImage');
        expect(socMajorDialogPage.getSocMajorPreviewImageInput()).toMatch('socMajorPreviewImage');
        socMajorDialogPage.save();
        expect(socMajorDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class SocMajorComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-soc-major-my-suffix div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class SocMajorDialogPage {
    modalTitle = element(by.css('h4#mySocMajorLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    socMajorNameInput = element(by.css('input#field_socMajorName'));
    socMajorCodeInput = element(by.css('input#field_socMajorCode'));
    socMajorAvatorInput = element(by.css('input#file_socMajorAvator'));
    socMajorDescriptionInput = element(by.css('input#field_socMajorDescription'));
    socMajorURLInput = element(by.css('input#field_socMajorURL'));
    socMajorPreviewImageInput = element(by.css('input#field_socMajorPreviewImage'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setSocMajorNameInput = function (socMajorName) {
        this.socMajorNameInput.sendKeys(socMajorName);
    }

    getSocMajorNameInput = function () {
        return this.socMajorNameInput.getAttribute('value');
    }

    setSocMajorCodeInput = function (socMajorCode) {
        this.socMajorCodeInput.sendKeys(socMajorCode);
    }

    getSocMajorCodeInput = function () {
        return this.socMajorCodeInput.getAttribute('value');
    }

    setSocMajorAvatorInput = function (socMajorAvator) {
        this.socMajorAvatorInput.sendKeys(socMajorAvator);
    }

    getSocMajorAvatorInput = function () {
        return this.socMajorAvatorInput.getAttribute('value');
    }

    setSocMajorDescriptionInput = function (socMajorDescription) {
        this.socMajorDescriptionInput.sendKeys(socMajorDescription);
    }

    getSocMajorDescriptionInput = function () {
        return this.socMajorDescriptionInput.getAttribute('value');
    }

    setSocMajorURLInput = function (socMajorURL) {
        this.socMajorURLInput.sendKeys(socMajorURL);
    }

    getSocMajorURLInput = function () {
        return this.socMajorURLInput.getAttribute('value');
    }

    setSocMajorPreviewImageInput = function (socMajorPreviewImage) {
        this.socMajorPreviewImageInput.sendKeys(socMajorPreviewImage);
    }

    getSocMajorPreviewImageInput = function () {
        return this.socMajorPreviewImageInput.getAttribute('value');
    }

    save() {
        this.saveButton.click();
    }

    close() {
        this.closeButton.click();
    }

    getSaveButton() {
        return this.saveButton;
    }
}
