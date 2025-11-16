package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.RetryAnalyzer;
import utils.TestNGListener;

@Listeners(TestNGListener.class)

public class LoginTests extends ApplicationManager {
    //dsc@mail.ru
    //Password123!

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void loginPositiveTest(){
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm("dsc@mail.ru", "Password123!");
        Assert.assertTrue(new ContactsPage(getDriver()).isTextContactsPresent("CONTACTS"));
    }

    @Test
    public void loginNegativeTest_wrongPassword(){
        User user = new User("dsc@mail.ru", "ssword123!");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginFormWithUser(user);
        Assert.assertEquals(loginPage.closeAlertReturnText(), "Wrong email or password");
    }
}
