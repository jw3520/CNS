package community.crawler;

import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import community.dto.ArticleInfo;
import community.util.Util;

public class Crawler_etoland implements Crawler {
	//이토랜드, 인기게시판
	private String homeUrl = "https://www.etoland.co.kr";
	private String articlesUrl = "https://www.etoland.co.kr/bbs/board.php?bo_table=hit";
	private Document doc = null;
	private ArrayList<ArticleInfo> articles = new ArrayList<>();
		
	public Crawler_etoland() {
		try {
			doc = Jsoup.connect(articlesUrl).get();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getName() {
		return "etoland";
	}

	@Override
	public String getLogo() {
		return "css/logo/etoland.jpg";
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
		Iterator<Element> elements_title = doc.select(".mw_basic_list_subject span").iterator();
		Iterator<Element> elements_commentCount = doc.select(".mw_basic_list_comment_count").iterator();
		Iterator<Element> elements_date = doc.select(".mw_basic_list_datetime").iterator();
		ArrayList<ArticleInfo> temp = new ArrayList<>();
		Element element_title = null;
		
		if(elements_title.hasNext() && elements_date.hasNext()) {
			elements_title.next();
			elements_date.next();
			elements_date.next();
		}
			
		for(int i = 0; i < 10; i++) {
			if(elements_title.hasNext())
				element_title = elements_title.next();
			if(elements_commentCount.hasNext())
				commentCount = elements_commentCount.next().text();
			if(elements_date.hasNext()) {
				date = elements_date.next().text();
			}
			
			if(element_title != null) {
				url = element_title.parent().attr("abs:href");
				title = element_title.text();
			}
			
			temp.add(new ArticleInfo().setUrl(url)
									  .setTitle(title)
									  .setCommentCount(commentCount.equals("") ? commentCount : "[" + commentCount.substring(1, commentCount.length() - 1) + "]")
									  .setDate(date.contains("-")?Util.dateTypeChange(date, "MM-dd"):Util.dateTypeChange(date, "hh:mm")));
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