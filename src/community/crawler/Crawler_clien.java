package community.crawler;

import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import community.dto.ArticleInfo;
import community.util.Util;

public class Crawler_clien implements Crawler {
	//클리앙, 모두의공원
	private String homeUrl = "https://www.clien.net";
	private String articlesUrl = "https://www.clien.net/service/board/park?&od=T33";
	private Document doc = null;
	private ArrayList<ArticleInfo> articles = new ArrayList<>();
	
	public Crawler_clien() {
		try {
			doc = Jsoup.connect(articlesUrl).get();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getName() {
		return "clien";
	}

	@Override
	public String getLogo() {
		return "css/logo/clien.png";
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
		Iterator<Element> elements_url = doc.select(".list_subject").iterator();
		Iterator<Element> elements_title = doc.select(".subject_fixed").iterator();
		Iterator<Element> elements_commentCount = doc.select(".rSymph05").iterator();
		Iterator<Element> elements_date = doc.select(".timestamp").iterator();
		ArrayList<ArticleInfo> temp = new ArrayList<>();
		
		if(elements_url.hasNext() && elements_date.hasNext()) {
			elements_url.next();
			elements_date.next();
		}
		
		for(int i = 0; i < 30; i++) {
			if(elements_url.hasNext())
				url = elements_url.next().attr("abs:href");
			if(elements_title.hasNext())
				title = elements_title.next().attr("title");
			if(elements_commentCount.hasNext())
				commentCount = elements_commentCount.next().text();
			if(elements_date.hasNext())
				date = elements_date.next().text();

			temp.add(new ArticleInfo().setUrl(url)
									  .setTitle(title)
									  .setCommentCount(commentCount.equals("") ? "" : "[" + commentCount + "]")
									  .setDate(Util.dateTypeChange(date, "yyyy-MM-dd hh:mm:ss")));
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