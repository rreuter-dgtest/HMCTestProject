package program;

import java.util.List;

public class Program {

	public static void main(String[] args) throws Exception {
		String testUserName = "catalyst-automation-testing";
		
		github.selenium.Github ghSelenium = new github.selenium.Github();
		List<String> repoNamesFromWebSite = ghSelenium.getSortedReposBySearchingForUser(testUserName);
		ghSelenium.closeBrowser();
		
		github.api.Repositories ghAPI = new github.api.Repositories();
		List<String> repoNamesFromAPI = ghAPI.getSortedGithubRepositoryNamesForUser(testUserName, github.api.Repositories.sortOptions.pushed);
		
		if (repoNamesFromWebSite.equals(repoNamesFromAPI)) {
			System.out.println("Results from Website and API match as expected!");
		} 
		else {
			System.out.println("The ordered list of repos from the Website and the API do not match as expected!");
		}
	}
}