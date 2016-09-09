package github.selenium.repositories;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;

import github.selenium.GithubCurrentPage;

public class Repositories extends GithubCurrentPage {
	
	private String repositoryNavTabSelectors = "nav.tabnav-tabs a";
	private String repositoryNavTabListItemsSelectors = "div.repo-list-item h3 a";
	
	public static enum tabOptions {
		contributions, repositories, activity;
	}
	
	public void selectNavTab(tabOptions options) {
		WebElement navTarget = super.getWebElements(this.repositoryNavTabSelectors).stream().filter(link -> link.getText().trim().toLowerCase().contains(options.toString())).collect(Collectors.toList()).get(0);
		super.clickWebElement(navTarget);
		super.WaitForJavascriptLoadComplete();
	}
	
	public List<String> getSortedGithubRepositoryName() {
		return super.getWebElements(this.repositoryNavTabListItemsSelectors).stream().map(ele -> ele.getText()).collect(Collectors.toList());
	}
}
