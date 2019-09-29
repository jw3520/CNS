package community.crawler;

import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import community.dto.ArticleInfo;

public class Crawler_fmkorea implements Crawler {
	//에펨코리아, 포텐터진 게시판
	private String homeUrl = "https://www.fmkorea.com";
	private String articlesUrl = "https://www.fmkorea.com/best";
	private Document doc = null;
	private ArrayList<ArticleInfo> articles = new ArrayList<>();
		
	public Crawler_fmkorea() {
		try {
			doc = Jsoup.connect(articlesUrl)
					   //.header("Content-Type", "application/json;charset=UTF-8")
					   //.userAgent(USER_AGENT)
					   .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.38 Safari/537.36")
					   .ignoreHttpErrors(true)
					   //.followRedirects(true)
					   .timeout(10000)
					   .ignoreContentType(true)
					   .cookie("auth", "token")
					   .get();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
			
	@Override
	public String getName() {
		return "fmkorea";
	}

	@Override
	public String getLogo() {
		return "css/logo/fmkorea.png";
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
		Iterator<Element> elements_title = doc.select("a.title").iterator();
		Iterator<Element> elements_commentCount = doc.select("a.comment_count").iterator();
		Iterator<Element> elements_subInfo = doc.select("span.voted_count").iterator();
		ArrayList<ArticleInfo> temp = new ArrayList<>();
		Element element_title = null;
		
		for(int i = 0; i < 11; i++) {
			if(elements_title.hasNext())
				element_title = elements_title.next();
			if(elements_commentCount.hasNext()) {
				commentCount = elements_commentCount.next().text();
			}
			if(elements_subInfo.hasNext()) {
				subInfo = "추천 " + elements_subInfo.next().text();
			}
				
			if(element_title != null) {
				url = element_title.attr("abs:href");
				title = element_title.text();
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