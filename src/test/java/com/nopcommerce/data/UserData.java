package com.nopcommerce.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserData {
	public static class Register {

		public static final String FIRST_NAME = "Anh";
		public static final String LAST_NAME = "IT";
		public static final String EMAIL = "AnhIT2022@gmail.com";
		public static final String INVALIDEMAIL = "AnhIT#$.gmail.com";
		public static final String PASSWORD = "123456";
		public static final String INVALID_PASSWORD = "123";
		public static final String DATE = "25";
		public static final String MONTH = "January";
		public static final String YEAR = "2001";
		public static final String COMPANY = "AnhIT Club";
	}
	public static class Address {

		public static final String FIRST_NAME_ADDRESS = "Bill";
		public static final String LAST_NAME_ADDRESS = "Nguyen";
		public static final String EMAIL_ADDRESS = "BillNguyen@gmail.com";
		public static final String COMPANY_ADDRESS = "AnhIT Club";
		public static final String COUNTRY_ADDRESS = "Viet Nam";
		public static final String CITY_ADDRESS = "Ho Chi Minh City";
		public static final String ADDRESS1 = "Binh Thanh, Ho Chi Minh City";
		public static final String ZIP_POSTAL_CODE = "00700";
		public static final String PHONE_NUMBER = "0945676875";
	}
	
	public static class ChangePass {

		public static final String NEW_PASSWORD = "123456789";
		public static final String CONFIRM_NEW_PASSWORD = "123456789";

	}
	
	public static class ReviewProduct {

		public static final String TITLE = "Review Example";
		public static final String REVIEW_TEXT = "Very godd";

	}
	public static class Product {

		public static final String[] PRODUCTNAME = {"Apple MacBook Pro 13-inch","Asus N551JK-XO076H Laptop","HP Envy 6-1180ca 15.6-Inch Sleekbook"
				,"HP Spectre XT Pro UltraBook","Lenovo Thinkpad X1 Carbon Laptop","Samsung Series 9 NP900X4C Premium Ultrabook"};

	}
	
	public static class Order_Product {

		public static final String Processor = "2.2 GHz Intel Pentium Dual-Core E2200";
		public static final String RAM = "8GB [+$60.00]";
		public static final String QUANITY = "2";
		public static final String HDD = "400 GB [+$100.00]";
		public static final String OS = "Vista Home [+$50.00]";
		public static final String Software  = "Acrobat Reader [+$10.00]";
		public static final String Software_edit  = "Microsoft Office [+$50.00]";
		public static final String PRICE = "$1,420.00";
		public static final String PRICE_EDIT = "$2,920.00";
	}
	
	public static class Shiping_Address {

		public static final String Country = "Viet Nam";
		public static final String ZIP_POSTAL_CODE = "00700";
		public static final String QUANITY = "";
		public static final String HDD = "";
		public static final String OS = "";
		public static final String Software  = "";
		public static final String Software_edit  = "]";
		public static final String PRICE = "";
		public static final String PRICE_EDIT = "";
	}
	
}
