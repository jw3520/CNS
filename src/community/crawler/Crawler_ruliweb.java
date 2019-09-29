package community.crawler;

import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import community.dto.ArticleInfo;
import community.util.Util;

public class Crawler_ruliweb implements Crawler {
	//루리웹, 많이 본 글/힛갤
	private String homeUrl = "https://www.ruliweb.com";
	private String articlesUrl = "https://bbs.ruliweb.com/best/selection?type=selection&orderby=replycount&range=24h";
	private Document doc = null;
	private ArrayList<ArticleInfo> articles = new ArrayList<>();
	
	public Crawler_ruliweb() {
		try {
			doc = Jsoup.connect(articlesUrl).get();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getName() {
		return "ruliweb";
	}

	@Override
	public String getLogo() {
		return "css/logo/ruliweb.png";
	}

	@Override
	public String getHomeUrl() {
		return homeUrl;
	}

	@Override
	public String getMoreUrl() {
		return articlesUrl;
	}

	@Override
	public ArrayList<ArticleInfo> getArticles() {
		String url = "", title = "", commentCount = "", date = "";
		Iterator<Element> elements_title = doc.select(".subject a").iterator();
		Iterator<Element> elements_commentCount = doc.select(".num").iterator();
		Iterator<Element> elements_date = doc.select(".time").iterator();
		Element element_title = null;
		
		elements_date.next();
		for(int i = 0; i < 10; i++) {
			if(elements_title.hasNext())
				element_title = elements_title.next();
			if(elements_commentCount.hasNext())
				commentCount = elements_commentCount.next().text();
			if(elements_date.hasNext()) {
				date = elements_date.next().text();
			}
			
			if(element_title != null) {
				url = element_title.attr("abs:href");
				title = element_title.text();
			}
			
			articles.add(new ArticleInfo().setUrl(url)
									  .setTitle(title)
									  .setCommentCount(commentCount.equals("") ? commentCount : "[" + commentCount + "]")
									  .setDate(date.contains(".")?Util.dateTypeChange(date, "yy.MM.dd"):Util.dateTypeChange(date, "hh:mm")));
		}
		return articles;
	}
}