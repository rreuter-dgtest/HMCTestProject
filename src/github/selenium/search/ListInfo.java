package github.selenium.search;

import org.openqa.selenium.WebElement;

import github.selenium.GithubCurrentPage;

public class ListInfo extends GithubCurrentPage {

	private String listInfoLinkText = "div.user-list-info a em";
	
	public void clickUserListItem(String itemTitle) {
		WebElement itemTitleElement = super.getWebElement(this.listInfoLinkText);
		WebElement itemTitleLinkElement = super.getParentOfWebElement(itemTitleElement);
		super.clickWebElement(itemTitleLinkElement);
		super.WaitForJavascriptLoadComplete();
	}	
}
