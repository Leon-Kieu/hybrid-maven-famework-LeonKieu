package pageUI.nopcommerce.admin;

public class BasePageAdminUI {
	public static final String DYNAMIC_MENU_ITEM_BY_NAME = "xpath=//li[@class='nav-item has-treeview']/a/p[contains(text(),'%s')]";
	public static final String DYNAMIC_SUB_MENU_ITEM_BY_NAME = "xpath=//li[@class='nav-item has-treeview menu-is-opening menu-open']/a/p[contains(text(),'%s')]/ancestor::a/following-sibling::ul[@class='nav nav-treeview']//p[contains(text(),'%s')]";
	
}
