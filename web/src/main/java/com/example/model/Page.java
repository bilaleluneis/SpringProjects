package com.example.model;

import org.apache.log4j.Logger;

/**
 * @author Bilal El Uneis (bilaleluneis@gmail.com)
 * @since April 2019
 */

public class Page {
	
	private static final Logger log = Logger.getLogger(Page.class);
	public static final String MODEL = "pageModelToView";
	private Integer pageId;
	private String tileName;
	private String url;
	private String text;
	
	private Page() {
		log.info("new Page instance created with following parameters:");
	}
	
	public Page(final String text, final Integer pageId, final String tileName, final String url) {
		this();
		this.text = new String(text);
		this.pageId = new Integer(pageId);
		this.tileName = new String(tileName);
		this.url = new String(url);
		log.info("pageId = " + this.pageId.toString());
		log.info("tileName = " + this.tileName);
		log.info("url = " + this.url);
		log.info("text = " + this.text);
	}

	public Integer getPageId() {
		return pageId;
	}

	public String getTileName() {
		return tileName;
	}
	
	public String getUrl() {
		return url;
	}

	public String getText() {
		return text;
	}

}
