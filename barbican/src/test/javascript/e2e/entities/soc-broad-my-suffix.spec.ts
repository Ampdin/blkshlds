import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('SocBroad e2e test', () => {

    let navBarPage: NavBarPage;
    let socBroadDialogPage: SocBroadDialogPage;
    let socBroadComponentsPage: SocBroadComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load SocBroads', () => {
        navBarPage.goToEntity('soc-broad-my-suffix');
        socBroadComponentsPage = new SocBroadComponentsPage();
        expect(socBroadComponentsPage.getTitle()).toMatch(/barbicanApp.socBroad.home.title/);

    });

    it('should load create SocBroad dialog', () => {
        socBroadComponentsPage.clickOnCreateButton();
        socBroadDialogPage = new SocBroadDialogPage();
        expect(socBroadDialogPage.getModalTitle()).toMatch(/barbicanApp.socBroad.home.createOrEditLabel/);
        socBroadDialogPage.close();
    });

    it('should create and save SocBroads', () => {
        socBroadComponentsPage.clickOnCreateButton();
        socBroadDialogPage.setSocBroadNameInput('socBroadName');
        expect(socBroadDialogPage.getSocBroadNameInput()).toMatch('socBroadName');
        socBroadDialogPage.setSocBroadCodeInput('socBroadCode');
        expect(socBroadDialogPage.getSocBroadCodeInput()).toMatch('socBroadCode');
        socBroadDialogPage.setSocBroadAvatorInput(absolutePath);
        socBroadDialogPage.setSocBroadDescriptionInput('socBroadDescription');
        expect(socBroadDialogPage.getSocBroadDescriptionInput()).toMatch('socBroadDescription');
        socBroadDialogPage.setSocBroadURLInput('socBroadURL');
        expect(socBroadDialogPage.getSocBroadURLInput()).toMatch('socBroadURL');
        socBroadDialogPage.setSocBroadPreviewImageInput('socBroadPreviewImage');
        expect(socBroadDialogPage.getSocBroadPreviewImageInput()).toMatch('socBroadPreviewImage');
        socBroadDialogPage.socMinorSelectLastOption();
        socBroadDialogPage.save();
        expect(socBroadDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class SocBroadComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-soc-broad-my-suffix div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class SocBroadDialogPage {
    modalTitle = element(by.css('h4#mySocBroadLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    socBroadNameInput = element(by.css('input#field_socBroadName'));
    socBroadCodeInput = element(by.css('input#field_socBroadCode'));
    socBroadAvatorInput = element(by.css('input#file_socBroadAvator'));
    socBroadDescriptionInput = element(by.css('input#field_socBroadDescription'));
    socBroadURLInput = element(by.css('input#field_socBroadURL'));
    socBroadPreviewImageInput = element(by.css('input#field_socBroadPreviewImage'));
    socMinorSelect = element(by.css('select#field_socMinor'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setSocBroadNameInput = function (socBroadName) {
        this.socBroadNameInput.sendKeys(socBroadName);
    }

    getSocBroadNameInput = function () {
        return this.socBroadNameInput.getAttribute('value');
    }

    setSocBroadCodeInput = function (socBroadCode) {
        this.socBroadCodeInput.sendKeys(socBroadCode);
    }

    getSocBroadCodeInput = function () {
        return this.socBroadCodeInput.getAttribute('value');
    }

    setSocBroadAvatorInput = function (socBroadAvator) {
        this.socBroadAvatorInput.sendKeys(socBroadAvator);
    }

    getSocBroadAvatorInput = function () {
        return this.socBroadAvatorInput.getAttribute('value');
    }

    setSocBroadDescriptionInput = function (socBroadDescription) {
        this.socBroadDescriptionInput.sendKeys(socBroadDescription);
    }

    getSocBroadDescriptionInput = function () {
        return this.socBroadDescriptionInput.getAttribute('value');
    }

    setSocBroadURLInput = function (socBroadURL) {
        this.socBroadURLInput.sendKeys(socBroadURL);
    }

    getSocBroadURLInput = function () {
        return this.socBroadURLInput.getAttribute('value');
    }

    setSocBroadPreviewImageInput = function (socBroadPreviewImage) {
        this.socBroadPreviewImageInput.sendKeys(socBroadPreviewImage);
    }

    getSocBroadPreviewImageInput = function () {
        return this.socBroadPreviewImageInput.getAttribute('value');
    }

    socMinorSelectLastOption = function () {
        this.socMinorSelect.all(by.tagName('option')).last().click();
    }

    socMinorSelectOption = function (option) {
        this.socMinorSelect.sendKeys(option);
    }

    getSocMinorSelect = function () {
        return this.socMinorSelect;
    }

    getSocMinorSelectedOption = function () {
        return this.socMinorSelect.element(by.css('option:checked')).getText();
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
