package com.ssafy.happyhouse.model;

//import lombok.AllArgsConstructor;
import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Builder
public class NewsDto {
//    private String no;
//	private String url;
//	private String imgUrl;
//	private String title;
//	private String contents;
//	private String time;

	String url;
	String imgUrl;
	String title;
	String contents;
	String time;
	String writing;

	public NewsDto(String url, String imgUrl, String title, String contents, String time, String writing) {
		super();
		this.url = url;
		this.imgUrl = imgUrl;
		this.title = title;
		this.contents = contents;
		this.time = time;
		this.writing = writing;
	}

	public NewsDto() {
		super();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getWriting() {
		return writing;
	}

	public void setWriting(String writing) {
		this.writing = writing;
	}

	@Override
	public String toString() {
		return "NewsDto [url=" + url + ", imgUrl=" + imgUrl + ", title=" + title + ", contents=" + contents + ", time="
				+ time + ", writing=" + writing + "]";
	}
}
