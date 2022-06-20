package com.ssafy.happyhouse.component;

import com.ssafy.happyhouse.model.NewsDto;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.text.SimpleDateFormat;

// 네이버 뉴스 '부동산' 검색

@Component
public class JsoupComponent {

	// ===================== 처음 뉴스 목록 가져올 때 =========================

	// 페이지 입장 당 한 번
	public List<NewsDto> getNewsList() {
		// System.out.println("public List<NewsDto> getNewsList()");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c1 = Calendar.getInstance();
		String strToday = sdf.format(c1.getTime());
		List<NewsDto> list = new ArrayList<>();
		// 페이지가 1~5
		for (int i = 1; i <= 5; i++) {
			final String newsList = "https://news.naver.com/main/list.naver?mode=LS2D&sid2=260&sid1=101&mid=shm&date="
					+ strToday + "&page=" + i;
			// System.out.println(newsList);
			Connection conn = Jsoup.connect(newsList);
			try {
				Document document = conn.get();
				list.addAll(getNewsList(document));
			} catch (IOException ignored) {

			}
		}
		// 페이지 하나만 가져오기(20개)
//		final String newsList = "https://news.naver.com/main/list.naver?mode=LS2D&sid2=260&sid1=101&mid=shm&date="
//				+ strToday + "&page=1";
//		//System.out.println(newsList);
//		Connection conn = Jsoup.connect(newsList);
//		try {
//			Document document = conn.get();
//			list.addAll(getNewsList(document));
//		} catch (IOException ignored) {
//
//		}
		// System.out.println(list);
		return list;
	}

	// 페이지 하나당 한 번
	public List<NewsDto> getNewsList(Document document) {
		// System.out.println("public List<NewsDto> getNewsList(Document document)");
		Elements newsElement = document.select("div.list_body");
		// System.out.println(newsElement);
		List<NewsDto> list = new ArrayList<>();
		// 10개만 가져가기
//		for (int i = 0; i < 10; i++) {
//			Element element = newsElement.select("dl").get(i);
//			//System.out.println(element); // <dl>...</dl>
//			list.add(createNewsDto(element));
//			
//		}
		// 20개 전부 가져오기
		for (Element element : newsElement.select("dl")) {
			// System.out.println(element); // <dl>...</dl>
			list.add(createNewsDto(element));
		}
		return list;
	}

	// 기사 하나당 한 번
	public NewsDto createNewsDto(Element dt) {
		// System.out.println("public NewsDto createNewsDto(Element dt)");
		NewsDto newsDto = new NewsDto();

		// System.out.println("----------setDto--------");
		newsDto.setUrl(dt.select("a").attr("href"));
		// newsDto.setImgUrl(dt.select("img").attr("src"));
		newsDto.setImgUrl(dt.select("img").attr("src").equals("")
				? "https://ssl.pstatic.net/static.news/image/news/2009/noimage_106x72.png"
				: dt.select("img").attr("src"));
		// System.out.println(newsDto.getImgUrl());
		newsDto.setTitle(dt.select("dt").text());
		String contents = dt.select(".lede").text();
		// 내용 길이
		if (contents.length() > 57)
			contents = contents.substring(0, 57) + "...";
		newsDto.setContents(contents);
		newsDto.setTime(dt.select(".date").text());
		newsDto.setWriting(dt.select(".writing").text());
		// System.out.println(newsDto.getWriting());
		// System.out.println("==========================");

		return newsDto;
	}

	// dto가 private일 때
//	public NewsDto createNewsDto(Element dt) {
//		System.out.println("public NewsDto createNewsDto(Element dt)");
//		NewsDto newsDto = NewsDto.builder().build();
//		Class<?> clazz = newsDto.getClass();
//		Field[] fields = clazz.getDeclaredFields();
//
//		String title;
//		String url;
//		String contents;
//		String time;
//		// Dto 아직 수정 안 함
//		//String imgUrl;
//
//		System.out.println("-------------------");
//		//imgUrl = dt.select("img").attr("src");
//		title = dt.select("dt").text();
//		System.out.println(title);
//		url = dt.select("a").attr("href");
//		System.out.println(url);
//		System.out.println("=======================");
//		contents = dt.select(".lede").text();
//		// 내용 길이
//		if (contents.length() > 61)
//			contents = contents.substring(0, 61) + "...";
//		time = dt.select(".date").text();
//
//		fields[0].setAccessible(true);
//		fields[1].setAccessible(true);
//		fields[2].setAccessible(true);
//		fields[3].setAccessible(true);
//		try {
//			fields[0].set(newsDto, title);
//			fields[1].set(newsDto, url);
//			fields[2].set(newsDto, contents);
//			fields[3].set(newsDto, time);
//		} catch (Exception ignored) {
//		}
//
//		return newsDto;
//	}

	// ==================================== 검색할 때 사용(손 안댐)
	// ==============================================

	public List<NewsDto> SearchNewsList(String search) {
		System.out.println("public List<NewsDto> SearchNewsList(String search)");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c1 = Calendar.getInstance();
		String strToday = sdf.format(c1.getTime());
		List<NewsDto> list = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			final String newsList = "https://news.naver.com/main/list.naver?mode=LS2D&sid2=260&sid1=101&mid=shm&date="
					+ strToday + "&page=" + i;
			Connection conn = Jsoup.connect(newsList);

			try {
				Document document = conn.get();
				list.addAll(SearchNewsList(document, search));
			} catch (IOException ignored) {
			}
		}
		return list;
	}

	public List<NewsDto> SearchNewsList(Document document, String search) {
		System.out.println("public List<NewsDto> SearchNewsList(Document document, String search");
		Elements newsElement = document.select("div.list_body");
		List<NewsDto> list = new ArrayList<>();
		for (Element element : newsElement.select("dl")) {
			NewsDto news = createNewsDtoSearch(element, search);
			if (news != null)
				list.add(news);
		}
		return list;
	}

	public NewsDto createNewsDtoSearch(Element dt, String search) {
		System.out.println("public NewsDto createNewsDtoS(Element dt, String search)");
		NewsDto newsDto = new NewsDto();

		newsDto.setUrl(dt.select("a").attr("href"));
		newsDto.setImgUrl(dt.select("img").attr("src").equals("")
				? "https://ssl.pstatic.net/static.news/image/news/2009/noimage_106x72.png"
				: dt.select("img").attr("src"));
		newsDto.setTitle(dt.select("dt").text());
		String contents = dt.select(".lede").text();
		if (contents.length() > 57)
			contents = contents.substring(0, 57) + "...";
		newsDto.setContents(contents);
		newsDto.setTime(dt.select(".date").text());
		newsDto.setWriting(dt.select(".writing").text());

		if (!(newsDto.getTitle().contains(search) || newsDto.getContents().contains(search)))
			return null;

		return newsDto;
	}
}