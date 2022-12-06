package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.CommentDto;
import com.example.demo.dto.MemberDto;

@Mapper
public interface CommentMapper {
	
	public int insertComment(CommentDto dto);
	public List<CommentDto> showAllComment(int bIdx);
	public int getCommentCount(int idx);
	public int deleteAllComment(int bIdx);
	public int deleteSelectedComment(@Param("idx")int idx);
}
