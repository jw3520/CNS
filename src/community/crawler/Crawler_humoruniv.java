package community.crawler;

import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import community.dto.ArticleInfo;
import community.util.Util;

public class Crawler_humoruniv implements Crawler {
	//웃긴대학, 오늘의 베스트
	private String homeUrl = "https://www.humoruniv.com";
	private String articlesUrl = "https://web.humoruniv.com/board/humor/list.html?table=pds&st=day";
	private Document doc = null;
	private ArrayList<ArticleInfo> articles = new ArrayList<>();
	
	public Crawler_humoruniv() {
		try {
			doc = Jsoup.connect(articlesUrl).get();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getName() {
		return "humoruniv";
	}

	@Override
	public String getLogo() {
		return "css/logo/humoruniv.gif";
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
		Iterator<Element> elements_title = doc.select(".li_sbj a").iterator();
		Iterator<Element> elements_commentCount = doc.select(".list_comment_num").iterator();
		Iterator<Element> elements_date = doc.select(".w_date").iterator();
		Iterator<Element> elements_time = doc.select(".w_time").iterator();
		ArrayList<ArticleInfo> temp = new ArrayList<>();
		Element element_title = null;
		
		for(int i = 0; i < 20; i++) {
			if(elements_title.hasNext())
				element_title = elements_title.next();
			if(elements_commentCount.hasNext())
				commentCount = elements_commentCount.next().text();
			if(elements_date.hasNext() && elements_time.hasNext())
				date = elements_date.next().text() + " " + elements_time.next().text();
			
			if(element_title != null) {
				url = element_title.attr("abs:href");
				title = element_title.text().substring(0, element_title.text().length() - 5);
			}
			
			temp.add(new ArticleInfo().setUrl(url)
									  .setTitle(title)
									  .setCommentCount(commentCount)
									  .setDate(Util.dateTypeChange(date, "yyyy-MM-dd hh:mm")));
		}
		
		if(!temp.get(5).getCommentCount().equals("")) {
			temp.sort(null); // null은 Comparator.naturalOrder()과 같다.
		}
		
		for(int i = 0; i < 10; i++) {
			articles.add(temp.get(i));
		}
		return articles;
	}
}