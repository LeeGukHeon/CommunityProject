package com.example.demo.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardDto {
	public BoardDto(int idx,String boardtitle,String boardtitlekr, String title,String id, String writer, String content,int views,int cCount, String regdate) {
		super();
		this.idx = idx;
		this.boardtitle = boardtitle;
		this.boardtitlekr = boardtitlekr;
		this.title = title;
		this.id = id;
		this.writer = writer;
		this.content = content;
		this.regdate = regdate;
		this.views = views;
		this.cCount = cCount;
	}

	private int idx;
	private String boardtitle;
	private String boardtitlekr;
	private String title;
	private String id;
	private String writer;
	private String content;
	private int views;
	private int cCount;
	private String regdate;
	
	public BoardDto() {
		// TODO Auto-generated constructor stub
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getBoardtitle() {
		return boardtitle;
	}

	public void setBoardtitle(String boardtitle) {
		this.boardtitle = boardtitle;
	}
	public String getBoardtitlekr() {
		return boardtitlekr;
	}

	public void setBoardtitlekr(String boardtitlekr) {
		this.boardtitlekr = boardtitlekr;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}
	
	public int cCount() {
		return cCount;
	}

	public void setCCount(int cCount) {
		this.cCount = cCount;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate){
		this.regdate = regdate;
	}
	
}
