package commons;

import org.openqa.selenium.WebDriver;

import pageObject.nopcommerce.user.UserAddressesPageObject;
import pageObject.nopcommerce.user.UserChangePasswordPageObject;
import pageObject.nopcommerce.user.UserCustomerInfoPageObject;
import pageObject.nopcommerce.user.UserHomePageObject;
import pageObject.nopcommerce.user.UserLoginPageObject;
import pageObject.nopcommerce.user.UserMyproductReviewPageObject;
import pageObject.nopcommerce.user.UserProductDetailPageObject;
import pageObject.nopcommerce.user.UserProductPageObject;
import pageObject.nopcommerce.user.UserProductReviewPageObject;
import pageObject.nopcommerce.user.UserRegisterPageObject;
import pageObject.nopcommerce.user.UserSearchPageObject;



public class PageGeneratorManager {


	public static UserHomePageObject getUserHomePage(WebDriver driver) {
		return new UserHomePageObject(driver);
	}
	
	public static UserRegisterPageObject getUserRegisterPage(WebDriver driver) {
		return new UserRegisterPageObject(driver);
	}
	
	public static UserLoginPageObject getUserLoginPage(WebDriver driver) {
		return new UserLoginPageObject(driver);
	}
	
	public static UserCustomerInfoPageObject getUserCustomerInfoPage(WebDriver driver) {
		return new UserCustomerInfoPageObject(driver);
	}
	
	public static UserAddressesPageObject getUserAddressesPage(WebDriver driver) {
		return new UserAddressesPageObject(driver);
	}

	public static UserChangePasswordPageObject getUserChangePasswordPage(WebDriver driver) {
		return new UserChangePasswordPageObject(driver);
	}
	
	public static UserMyproductReviewPageObject getUserMyProductReviewPage(WebDriver driver) {
		return new UserMyproductReviewPageObject(driver);
	}
	
	
	public static UserProductPageObject getUserProductPage(WebDriver driver) {
		return new UserProductPageObject(driver);
	}
	
	public static UserProductDetailPageObject getUserProductDetailPage(WebDriver driver) {
		return new UserProductDetailPageObject(driver);
	}
	
	public static UserProductReviewPageObject getUserProductReviewPage(WebDriver driver) {
		return new UserProductReviewPageObject(driver);
	}
	
	public static UserSearchPageObject getUserSearchPage(WebDriver driver) {
		return new UserSearchPageObject(driver);
	}

}
