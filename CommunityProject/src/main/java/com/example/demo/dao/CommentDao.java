package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.CommentDto;
import com.example.demo.mapper.CommentMapper;

@Repository
public class CommentDao implements CommentMapper {
	
	@Autowired
	private CommentMapper commentmapper;

	@Override
	public int insertComment(CommentDto dto) {
		// TODO Auto-generated method stub
		return commentmapper.insertComment(dto);
	}

	@Override
	public List<CommentDto> showAllComment(int bIdx) {
		// TODO Auto-generated method stub
		return commentmapper.showAllComment(bIdx);
	}

	@Override
	public int getCommentCount(int idx) {
		// TODO Auto-generated method stub
		return commentmapper.getCommentCount(idx);
	}

	@Override
	public int deleteAllComment(int bIdx) {
		// TODO Auto-generated method stub
		return commentmapper.deleteAllComment(bIdx);
	}

	@Override
	public int deleteSelectedComment(int idx) {
		// TODO Auto-generated method stub
		return commentmapper.deleteSelectedComment(idx);
	}
}
