package com.interapt.android.InsiderLouisville.requestresponse;

import java.net.URL;
import java.util.ArrayList;

import org.json.JSONObject;

/**
 * @author rob
 * 
 * This class is used to store all the information about a given article
 * that is returned from an RSS feed. It is based on the RSS2.0 definition and
 * contains the variables for select core attributes in the schema
 *
 */
public class News   {

	public boolean isSelected = false;
	private long articleId =0l;
	private long feedId;
	private String title;
	private String description;
	private String imgLink;
	private String pubDate;
	private URL url;
	private String comments;
	private String dc_creator;
	private URL guid;
	private URL wfw_commentRss;
	private int slash_comments;
	private JSONObject jsonObject;
	public JSONObject getJsonObject() {
		return jsonObject;
	}
	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getDc_creator() {
		return dc_creator;
	}
	public void setDc_creator(String dc_creator) {
		this.dc_creator = dc_creator;
	}
	public URL getGuid() {
		return guid;
	}
	public void setGuid(URL guid) {
		this.guid = guid;
	}
	public URL getWfw_commentRss() {
		return wfw_commentRss;
	}
	public void setWfw_commentRss(URL wfw_commentRss) {
		this.wfw_commentRss = wfw_commentRss;
	}
	public int getSlash_comments() {
		return slash_comments;
	}
	public void setSlash_comments(int slash_comments) {
		this.slash_comments = slash_comments;
	}
	private ArrayList<String>  category = new ArrayList<String>();
	public ArrayList<String> getCategory() {
		return category;
	}
	public void setCategory(ArrayList<String> category) {
		this.category = category;
	}
	private String encodedContent;
	/**
	 * @return the articleId
	 */
	public long getArticleId() {
		return articleId;
	}
	/**
	 * @param articleId the articleId to set
	 */
	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}
	/**
	 * @return the feedId
	 */
	public long getFeedId() {
		return feedId;
	}
	/**
	 * @param feedId the feedId to set
	 */
	public void setFeedId(long feedId) {
		this.feedId = feedId;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the url
	 */
	public URL getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(URL url) {
		this.url = url;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;

		//parse description for any image or video links
		if (description.contains("<img ")){
			String img  = description.substring(description.indexOf("<img "));
			String cleanUp = img.substring(0, img.indexOf(">")+1);
			img = img.substring(img.indexOf("src=") + 5);
			int indexOf = img.indexOf("'");
			if (indexOf==-1){
				indexOf = img.indexOf("\"");
			}
			img = img.substring(0, indexOf);

			setImgLink(img);

			this.description = this.description.replace(cleanUp, "");
		}
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param pubDate the pubDate to set
	 */
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	/**
	 * @return the pubDate
	 */
	public String getPubDate() {
		return pubDate;
	}
	/**
	 * @param encodedContent the encodedContent to set
	 */
	public void setEncodedContent(String encodedContent) {
		this.encodedContent = encodedContent;
	}
	/**
	 * @return the encodedContent
	 */
	public String getEncodedContent() {
		return encodedContent;
	}
	/**
	 * @param imgLink the imgLink to set
	 */
	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}
	/**
	 * @return the imgLink
	 */
	public String getImgLink() {
		return imgLink;
	}


	public String getCategoryName(){
		int key = (int)articleId;
		switch (key) {
		case 1:

			return "BUSINESS";
		case 2:

			return "STARTUPS";
		case 3:

			return "METRO";
		case 4:

			return "LIFESTYLE & CULTURE";

		default:
			return "NO CATEGORY";
		}
	}
	
}
