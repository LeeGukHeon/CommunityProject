package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.BoardDto;
import com.example.demo.mapper.BoardMapper;

@Repository
public class BoardDao implements BoardMapper {
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public int insertBoardData(BoardDto dto) {
		// TODO Auto-generated method stub
		return boardMapper.insertBoardData(dto);
	}

	@Override
	public BoardDto selectBoardDataOne(int idx) {
		// TODO Auto-generated method stub
		return boardMapper.selectBoardDataOne(idx);
	}

	@Override
	public int updateBoardData(BoardDto dto) {
		// TODO Auto-generated method stub
		return boardMapper.updateBoardData(dto);
	}

	@Override
	public int deleteBoardData(int idx) {
		// TODO Auto-generated method stub
		return boardMapper.deleteBoardData(idx);
	}
	
	@Override
	public int selectBoardDataCount(String boardtitle) {
		// TODO Auto-generated method stub
		return boardMapper.selectBoardDataCount(boardtitle);
	}

	@Override
	public List<BoardDto> searchBoardData(String searchOption, String searchValue, int page) {
		// TODO Auto-generated method stub
		return boardMapper.searchBoardData(searchOption, searchValue, page);
	}
	@Override
	public List<BoardDto> boardPaging(int page, String boardtitle, int offset) {
		// TODO Auto-generated method stub
		return boardMapper.boardPaging(page, boardtitle, offset);
	}
	@Override
	public List<BoardDto> selectBoardDataAll(int offset) {
		// TODO Auto-generated method stub
		return boardMapper.selectBoardDataAll(offset);
	}
	@Override
	public int searchBoardDataCount(String searchOption, String searchValue) {
		// TODO Auto-generated method stub
		return boardMapper.searchBoardDataCount(searchOption, searchValue);
	}

	@Override
	public String recentPopular() {
		// TODO Auto-generated method stub
		return boardMapper.recentPopular();
	}

	@Override
	public int selectUserBoardCount(String writer) {
		// TODO Auto-generated method stub
		return boardMapper.selectUserBoardCount(writer);
	}

	@Override
	public int updateViews(int idx) {
		// TODO Auto-generated method stub
		return boardMapper.updateViews(idx);
	}

	@Override
	public int updateCCount(int idx) {
		// TODO Auto-generated method stub
		return boardMapper.updateCCount(idx);
	}

	@Override
	public List<BoardDto> selectUserBoardDataAll(String writer) {
		// TODO Auto-generated method stub
		return boardMapper.selectUserBoardDataAll(writer);
	}

	@Override
	public List<BoardDto> selectUserBoardDataprev(String writer) {
		// TODO Auto-generated method stub
		return boardMapper.selectUserBoardDataprev(writer);
	}

	@Override
	public int updateBoardUser(BoardDto dto) {
		// TODO Auto-generated method stub
		return boardMapper.updateBoardUser(dto);
	}

	@Override
	public int minusCCount(int idx) {
		// TODO Auto-generated method stub
		return boardMapper.minusCCount(idx);
	}	
}
