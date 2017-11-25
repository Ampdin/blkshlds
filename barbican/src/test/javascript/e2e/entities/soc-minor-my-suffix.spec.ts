import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('SocMinor e2e test', () => {

    let navBarPage: NavBarPage;
    let socMinorDialogPage: SocMinorDialogPage;
    let socMinorComponentsPage: SocMinorComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load SocMinors', () => {
        navBarPage.goToEntity('soc-minor-my-suffix');
        socMinorComponentsPage = new SocMinorComponentsPage();
        expect(socMinorComponentsPage.getTitle()).toMatch(/barbicanApp.socMinor.home.title/);

    });

    it('should load create SocMinor dialog', () => {
        socMinorComponentsPage.clickOnCreateButton();
        socMinorDialogPage = new SocMinorDialogPage();
        expect(socMinorDialogPage.getModalTitle()).toMatch(/barbicanApp.socMinor.home.createOrEditLabel/);
        socMinorDialogPage.close();
    });

    it('should create and save SocMinors', () => {
        socMinorComponentsPage.clickOnCreateButton();
        socMinorDialogPage.setSocMinorNameInput('socMinorName');
        expect(socMinorDialogPage.getSocMinorNameInput()).toMatch('socMinorName');
        socMinorDialogPage.setSocMinorCodeInput('socMinorCode');
        expect(socMinorDialogPage.getSocMinorCodeInput()).toMatch('socMinorCode');
        socMinorDialogPage.setSocMinorAvatorInput(absolutePath);
        socMinorDialogPage.setSocMinorDescriptionInput('socMinorDescription');
        expect(socMinorDialogPage.getSocMinorDescriptionInput()).toMatch('socMinorDescription');
        socMinorDialogPage.setSocMinorURLInput('socMinorURL');
        expect(socMinorDialogPage.getSocMinorURLInput()).toMatch('socMinorURL');
        socMinorDialogPage.setSocMinorPreviewImageInput('socMinorPreviewImage');
        expect(socMinorDialogPage.getSocMinorPreviewImageInput()).toMatch('socMinorPreviewImage');
        socMinorDialogPage.socMajorSelectLastOption();
        socMinorDialogPage.save();
        expect(socMinorDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class SocMinorComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-soc-minor-my-suffix div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class SocMinorDialogPage {
    modalTitle = element(by.css('h4#mySocMinorLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    socMinorNameInput = element(by.css('input#field_socMinorName'));
    socMinorCodeInput = element(by.css('input#field_socMinorCode'));
    socMinorAvatorInput = element(by.css('input#file_socMinorAvator'));
    socMinorDescriptionInput = element(by.css('input#field_socMinorDescription'));
    socMinorURLInput = element(by.css('input#field_socMinorURL'));
    socMinorPreviewImageInput = element(by.css('input#field_socMinorPreviewImage'));
    socMajorSelect = element(by.css('select#field_socMajor'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setSocMinorNameInput = function (socMinorName) {
        this.socMinorNameInput.sendKeys(socMinorName);
    }

    getSocMinorNameInput = function () {
        return this.socMinorNameInput.getAttribute('value');
    }

    setSocMinorCodeInput = function (socMinorCode) {
        this.socMinorCodeInput.sendKeys(socMinorCode);
    }

    getSocMinorCodeInput = function () {
        return this.socMinorCodeInput.getAttribute('value');
    }

    setSocMinorAvatorInput = function (socMinorAvator) {
        this.socMinorAvatorInput.sendKeys(socMinorAvator);
    }

    getSocMinorAvatorInput = function () {
        return this.socMinorAvatorInput.getAttribute('value');
    }

    setSocMinorDescriptionInput = function (socMinorDescription) {
        this.socMinorDescriptionInput.sendKeys(socMinorDescription);
    }

    getSocMinorDescriptionInput = function () {
        return this.socMinorDescriptionInput.getAttribute('value');
    }

    setSocMinorURLInput = function (socMinorURL) {
        this.socMinorURLInput.sendKeys(socMinorURL);
    }

    getSocMinorURLInput = function () {
        return this.socMinorURLInput.getAttribute('value');
    }

    setSocMinorPreviewImageInput = function (socMinorPreviewImage) {
        this.socMinorPreviewImageInput.sendKeys(socMinorPreviewImage);
    }

    getSocMinorPreviewImageInput = function () {
        return this.socMinorPreviewImageInput.getAttribute('value');
    }

    socMajorSelectLastOption = function () {
        this.socMajorSelect.all(by.tagName('option')).last().click();
    }

    socMajorSelectOption = function (option) {
        this.socMajorSelect.sendKeys(option);
    }

    getSocMajorSelect = function () {
        return this.socMajorSelect;
    }

    getSocMajorSelectedOption = function () {
        return this.socMajorSelect.element(by.css('option:checked')).getText();
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
