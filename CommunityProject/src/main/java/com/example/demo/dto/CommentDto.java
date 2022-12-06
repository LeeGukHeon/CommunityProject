package com.example.demo.dto;

public class CommentDto {
	public CommentDto(int idx, int bIdx, String cContent,String cWriter, String cRegdate) {
		super();
		this.idx = idx;
		this.bIdx = bIdx;
		this.cContent = cContent;
		this.cRegdate = cRegdate;
		this.cWriter = cWriter;
	}

	private int idx;
	private int bIdx;
	private String cContent;
	private String cWriter;
	private String cRegdate;
	
	public CommentDto() {
		// TODO Auto-generated constructor stub
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getbIdx() {
		return bIdx;
	}

	public void setbIdx(int bIdx) {
		this.bIdx = bIdx;
	}

	public String getcContent() {
		return cContent;
	}

	public void setcContent(String cContent) {
		this.cContent = cContent;
	}
	public String getcWriter() {
		return cWriter;
	}

	public void setcWriter(String cWriter) {
		this.cWriter = cWriter;
	}

	public String getcRegdate() {
		return cRegdate;
	}

	public void setcRegdate(String cRegdate) {
		this.cRegdate = cRegdate;
	}
	
	
}
