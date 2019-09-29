package community.crawler;

import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import community.dto.ArticleInfo;
import community.util.Util;

public class Crawler_ppomppu implements Crawler {
	//뽐뿌, HOT게시글
	private String homeUrl = "https://www.ppomppu.co.kr";
	private String articlesUrl = "https://www.ppomppu.co.kr/hot.php?category=2";
	private Document doc = null;
	private ArrayList<ArticleInfo> articles = new ArrayList<>();
	
	public Crawler_ppomppu() {
		try {
			doc = Jsoup.connect(articlesUrl).get();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getName() {
		return "ppomppu";
	}
	
	@Override
	public String getLogo() {
		return "css/logo/ppomppu.gif";
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
		Iterator<Element> elements_title = doc.select("img[alt=hot]").iterator();
		Iterator<Element> elements_commentCount = doc.select(".list_comment2").iterator();
		Iterator<Element> elements_date = doc.select(".board_date").iterator();
		ArrayList<ArticleInfo> temp = new ArrayList<>();
		Element element_title = null;
		
		for(int i = 0; i < 20; i++) {
			if(elements_title.hasNext())
				element_title = elements_title.next().parent();
			if(elements_commentCount.hasNext())
				commentCount = elements_commentCount.next().text();
			if(elements_date.hasNext()) {
				date = elements_date.next().text();
				elements_date.next();
				elements_date.next();
			}
			
			if(element_title != null) {
				url = element_title.attr("abs:href");
				title = element_title.text();
			}
			
			temp.add(new ArticleInfo().setUrl(url)
									  .setTitle(title)
									  .setCommentCount(commentCount.equals("") ? commentCount : "[" + commentCount + "]")
									  .setDate(date.contains("/")?Util.dateTypeChange(date, "yy/MM/dd"):Util.dateTypeChange(date, "hh:mm:ss")));
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