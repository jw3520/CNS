package community.crawler;

import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import community.dto.ArticleInfo;
import community.util.Util;

public class Crawler_theqoo implements Crawler {
	//더쿠, HOT 카테고리
	private String homeUrl = "https://theqoo.net";
	private String articlesUrl = "https://theqoo.net/hot?filter_mode=normal";
	private Document doc = null;
	private ArrayList<ArticleInfo> articles = new ArrayList<>();
		
	public Crawler_theqoo() {
		try {
			doc = Jsoup.connect(articlesUrl).get();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getName() {
		return "theqoo";
	}

	@Override
	public String getLogo() {
		return "css/logo/theqoo.png";
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
		Iterator<Element> elements_no = doc.select(".no").iterator();
		Iterator<Element> elements_title = doc.select(".title span").iterator();
		Iterator<Element> elements_date = doc.select(".time").iterator();
		ArrayList<ArticleInfo> temp = new ArrayList<>();
		Element element_title = null;
		
		if(elements_no.hasNext() || elements_title.hasNext()) {
			elements_no.next();
			elements_title.next();
		}
		
		while(elements_no.hasNext()) {
			String num = elements_no.next().text();
			if(num.charAt(0) >= 49 && num.charAt(0) <= 57) {
				break;
			} else {
				elements_title.next();
				elements_date.next();
			}
		}
		
		for(int i = 0; i < 27; i++) {
			if(elements_title.hasNext())
				element_title = elements_title.next();
			if(elements_date.hasNext())
				date = elements_date.next().text();
			
			if(element_title != null) {
				url = element_title.parent().attr("abs:href");
				title = element_title.text();
				commentCount = element_title.parent().parent().select(".replyNum").text();
			}
			
			temp.add(new ArticleInfo().setUrl(url)
									  .setTitle(title)
									  .setCommentCount(commentCount.equals("") ? commentCount : "[" + commentCount + "]")
									  .setDate(date.contains(".")?Util.dateTypeChange(date, "MM.dd"):Util.dateTypeChange(date, "hh:mm")));
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