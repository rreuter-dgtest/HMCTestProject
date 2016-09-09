package github.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;

//I added some comments, so there'd be an actual change.

public class Repositories {

	public static enum sortOptions {
		created, updated, pushed, full_name;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getSortedGithubRepositoryNamesForUser(String userName, sortOptions sortMethod) throws Exception {
		String repoURL = (this.getGithubUserInfo(userName)).get("repos_url");
		repoURL = repoURL.concat("?sort=".concat(sortMethod.toString()));
		ArrayList<Object> repoInfoList = new jsonutils.JsonUtils().convertComplexJsonToArrayList(this.getJsonResponseFromGithubURL(repoURL));
		return repoInfoList.stream().map(repoName -> ((Map<String, String>) repoName).get("name")).collect(Collectors.toList());
	}
	
	private Map<String, String> getGithubUserInfo(String userName) throws Exception {
		String userInfoURL = String.format("https://api.github.com/users/%s", userName);
		String json = this.getJsonResponseFromGithubURL(userInfoURL);
		return new jsonutils.JsonUtils().convertSimpleJsonToMap(json);
	}
	
	private String getJsonResponseFromGithubURL(String url) throws Exception {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		HttpResponse response = null;
		String responseString = "";
		try {
			response = client.execute(request);
			responseString = new BasicResponseHandler().handleResponse(response);
		} 
		catch (IOException e) {
			throw new Exception("Issue making connection with Github API!");
		}
		
		return responseString;
	}
}
