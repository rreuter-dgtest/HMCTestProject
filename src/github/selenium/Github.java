package github.selenium;

import java.util.List;

//Another comment to do better stuff

import org.openqa.selenium.Keys;

import github.selenium.repositories.Repositories;
import github.selenium.search.SearchPage;

public class Github extends webdriverutils.BrowserBase {
	
	private String searchFieldSelector = "input.header-search-input";
	private GithubCurrentPage currentPage = null;

	public Github() {
		this.goToHomePage();
	}
	
	public GithubCurrentPage getCurrentPage() {
		return currentPage;
	}
	
	public void goToHomePage() {
		super.GoToURL("http://github.com");
	}
	
	public void searchGithub(String searchString) throws Exception {
		super.typeIntoWebElement(this.searchFieldSelector, searchString + Keys.ENTER);
		super.WaitForJavascriptLoadComplete();
		if (!super.browserTitleContains(String.format("Search · %s · GitHub", searchString))) {
			throw new Exception(String.format("Cannot confirm search results for %s were loaded!", searchString));
		}
		this.currentPage = new github.selenium.search.SearchPage();
	}
	
	public List<String> getSortedReposBySearchingForUser(String userName) throws Exception {
		this.searchGithub(userName);
		((SearchPage) this.currentPage).selectLeftNavOption(github.selenium.search.SearchPage.leftNavOptions.users);
		((SearchPage) this.currentPage).getListInfoPanel().clickUserListItem(userName);
		this.currentPage = new Repositories();
		((Repositories) this.currentPage).selectNavTab(github.selenium.repositories.Repositories.tabOptions.repositories);
		return ((Repositories) this.currentPage).getSortedGithubRepositoryName();
	}
}