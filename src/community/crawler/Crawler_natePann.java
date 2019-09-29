package community.crawler;

import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import community.dto.ArticleInfo;

public class Crawler_natePann implements Crawler {
	//네이트 판, 톡커들의 선택 명예의전당 (실시간)
	private String homeUrl = "https://pann.nate.com";
	private String articlesUrl = "https://pann.nate.com/talk/ranking";
	private Document doc = null;
	private ArrayList<ArticleInfo> articles = new ArrayList<>();
	
	public Crawler_natePann() {
		try {
			doc = Jsoup.connect(articlesUrl).get();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getName() {
		return "nate_pann";
	}

	@Override
	public String getLogo() {
		return "css/logo/nate_pann.png";
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
		String url = "", title = "", commentCount = "", subInfo = "";
		Iterator<Element> elements_title = doc.select(".post_wrap dt a").iterator();
		Iterator<Element> elements_commentCount = doc.select(".reple-num").iterator();
		Iterator<Element> elements_subInfo = doc.select(".info").iterator();
		ArrayList<ArticleInfo> temp = new ArrayList<>();
		Element element_title = null;
		
		for(int i = 0; i < 10; i++) {
			if(elements_title.hasNext())
				element_title = elements_title.next();
			if(elements_commentCount.hasNext()) {
				commentCount = elements_commentCount.next().text();
				commentCount = commentCount.substring(1, commentCount.length() - 1);
			}
			if(elements_subInfo.hasNext()) {
				subInfo = elements_subInfo.next().text();
				subInfo = subInfo.substring(subInfo.indexOf("추천"), subInfo.length());
			}
				
			if(element_title != null) {
				url = element_title.attr("abs:href");
				title = element_title.attr("title");
			}
			
			temp.add(new ArticleInfo().setUrl(url)
									  .setTitle(title)
									  .setCommentCount(commentCount.equals("") ? "" : "[" + commentCount + "]")
									  .setDate(subInfo));
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