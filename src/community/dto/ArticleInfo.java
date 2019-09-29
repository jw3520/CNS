package community.dto;

public class ArticleInfo implements Comparable<ArticleInfo> {
	private String url;
	private String title;
	private String commentCount;
	private String date;
	
	public String getUrl() {
		return url;
	}
	public ArticleInfo setUrl(String url) {
		this.url = url;
		return this;
	}
	public String getTitle() {
		return title;
	}
	public ArticleInfo setTitle(String title) {
		this.title = title;
		return this;
	}
	public String getCommentCount() {
		return commentCount;
	}
	public ArticleInfo setCommentCount(String commentCount) {
		this.commentCount = commentCount;
		return this;
	}
	public String getDate() {
		return date;
	}
	public ArticleInfo setDate(String date) {
		this.date = date;
		return this;
	}
	@Override
	public int compareTo(ArticleInfo article) {
		int a1 = 0, a2 = 0;
		
		if(this.getCommentCount().contains("/")) {
			String temp = this.getCommentCount().split("/")[0];
			a1 = Integer.parseInt(temp.substring(1, temp.length()).replaceAll(",", ""));
		} else {
			a1 = Integer.parseInt(this.getCommentCount().substring(1, this.getCommentCount().length() - 1).replaceAll(",", ""));
		}
		
		if(article.getCommentCount().contains("/")) {
			String temp = article.getCommentCount().split("/")[0];
			a2 = Integer.parseInt(temp.substring(1, temp.length()).replaceAll(",", ""));
		} else {
			a2 = Integer.parseInt(article.getCommentCount().substring(1, article.getCommentCount().length() - 1).replaceAll(",", ""));
		}
		
		if (a1 < a2) {
            return 1;
        } else if (a1 > a2) {
            return -1;
        }
        return 0;
	}
}
