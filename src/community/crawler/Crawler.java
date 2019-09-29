package community.crawler;

import java.util.ArrayList;

import community.dto.ArticleInfo;

public interface Crawler {
	String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";
	public String getName();
	public String getLogo();
	public String getHomeUrl();
	public String getMoreUrl();
	public ArrayList<ArticleInfo> getArticles();
}
