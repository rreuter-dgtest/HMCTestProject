package github.selenium.search;

import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;

import github.selenium.GithubCurrentPage;

public class SearchPage extends GithubCurrentPage {
	
	private String menuDivSelector = "nav.menu";
	private String menuItemSelector = "a.menu-item";
	
	private ListInfo listInfoPanel = new ListInfo();	
	
	public ListInfo getListInfoPanel() {
		return listInfoPanel;
	}

	public static enum leftNavOptions {
		repositories, code, issues, users;
	}
	
	public void selectLeftNavOption(leftNavOptions option) {
		WebElement target = super.getWebElementInsideParentWebElement(super.getWebElement(menuDivSelector), this.menuItemSelector).stream().filter(link -> link.getAttribute("href").trim().toLowerCase().contains(option.toString())).collect(Collectors.toList()).get(0);
		super.clickWebElement(target);
		super.WaitForJavascriptLoadComplete();
	}
}