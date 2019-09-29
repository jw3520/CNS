package community.crawler;

import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import community.dto.ArticleInfo;

public class Crawler_daumCafe implements Crawler {
	//다음 카페 TOP, 카페인기글
	private String homeUrl = "https://top.cafe.daum.net";
	private String articlesUrl = "https://m.cafe.daum.net";
	private Document doc = null;
	private ArrayList<ArticleInfo> articles = new ArrayList<>();
	
	public Crawler_daumCafe() {
		try {
			doc = Jsoup.connect(articlesUrl).get();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getName() {
		return "daum_cafe";
	}
	
	@Override
	public String getLogo() {
		return "css/logo/daum_cafe.png";
	}

	@Override
	public String getHomeUrl() {
		return homeUrl;
	}

	@Override
	public String getMoreUrl() {
		return homeUrl;
	}

	@Override
	public ArrayList<ArticleInfo> getArticles() {
		String url = "", title = "", cafeName = "";
		Iterator<Element> elements_url = doc.select(".wrap_thumb").iterator();
		Iterator<Element> elements_title = doc.select(".tit_story").iterator();
		Iterator<Element> elements_cafeName = doc.select(".txt_cafename").iterator();
		
		for(int i = 0; i < 10; i++) {
			if(elements_url.hasNext())
				url = elements_url.next().parent().attr("abs:href");
			if(elements_title.hasNext())
				title = elements_title.next().text();
			if(elements_cafeName.hasNext())
				cafeName = elements_cafeName.next().text();
			
			articles.add(new ArticleInfo().setUrl(url)
										  .setTitle(title)
									  	  .setDate(cafeName.length() != 0 ? cafeName.substring(3, cafeName.length()) : ""));
		}
		return articles;
	}
}