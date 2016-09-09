package github.selenium;

import webdriverutils.BrowserBase;

/**
 * This exists as a "plug-in" for pages to be loaded into the currentpage property of the Githib class.
 * It's kinda ugly, since I haven't identified much in common on the pages to be loaded into currentpage,
 * but it should work as a polymorphism trick. 
 * 
 * It's a nod at creating something that might be extensible and scalable if there's need to extend in the future.
 * @author Rob
 *
 */
public class GithubCurrentPage extends BrowserBase {

}
