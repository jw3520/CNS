package community.crawler;

import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import community.dto.ArticleInfo;
import community.util.Util;

public class Crawler_dcinside implements Crawler {
	//디시인사이드, 초개념 갤러리
	private String homeUrl = "https://www.dcinside.com";
	private String articlesUrl = "https://gall.dcinside.com/board/lists/?id=superidea";
	private Document doc = null;
	private ArrayList<ArticleInfo> articles = new ArrayList<>();
	
	public Crawler_dcinside() {
		try {
			doc = Jsoup.connect(articlesUrl).get();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getName() {
		return "dcinside";
	}
	
	@Override
	public String getLogo() {
		return "css/logo/dcinside.png";
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
		Iterator<Element> elements_title = doc.select(".icon_toprecomimg").iterator();
		Iterator<Element> elements_commentCount = doc.select(".reply_num").iterator();
		Iterator<Element> elements_date = doc.select(".gall_date").iterator();
		ArrayList<ArticleInfo> temp = new ArrayList<>();
		Element element_title = null;
		
		for(int i = 0; i < 50; i++) {
			if(elements_title.hasNext())
				element_title = elements_title.next().parent();
			if(elements_commentCount.hasNext())
				commentCount = elements_commentCount.next().text();
			if(elements_date.hasNext())
				date = elements_date.next().attr("title");
				
			if(element_title != null) {
				url = element_title.attr("abs:href");
				title = element_title.text();
			}
			
			temp.add(new ArticleInfo().setUrl(url)
									  .setTitle(title)
									  .setCommentCount(commentCount)
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