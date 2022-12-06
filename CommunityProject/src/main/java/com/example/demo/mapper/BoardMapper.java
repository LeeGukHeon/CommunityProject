package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.BoardDto;


@Mapper
public interface BoardMapper {
	// 기본적으로 mybatis를 쓸 때 객체의 이름의 통일성을 위해서
	// 또 메서드의 관리를 위해서
	// mapper 인터페이스를 만들어 주는게 좋다
	
	 //입력
	public int insertBoardData(BoardDto dto);
	//하나 가져오기
	public BoardDto selectBoardDataOne(int idx);
	//수정
	public int updateBoardData(BoardDto dto);
	//삭제
	public int deleteBoardData(int idx); 
	//해당 게시판 글갯수 가져오기
	public int selectBoardDataCount(@Param("boardtitle") String boardtitle);
	//검색바 통합검색 게시판 구분 x
	public List<BoardDto> searchBoardData(@Param("searchOption") String searchOption, @Param("searchValue") String searchValue, @Param("page")int page);
	//메인 최근게시물 오프셋갯수대로 가져오기
	public List<BoardDto> selectBoardDataAll(int offset);
	//게시판 페이징처리
	public List<BoardDto> boardPaging(@Param("page")int page, @Param("boardtitle") String boardtitle, @Param("offset") int offset);
	//게시판 페이징처리용 전체 게시물 갯수 카운트
	public int searchBoardDataCount(@Param("searchOption") String searchOption, @Param("searchValue") String searchValue);
	public String recentPopular();
	public int selectUserBoardCount(String id);
	public int updateViews(int idx);
	public int updateCCount(int idx);
	public int minusCCount(int idx);
	public int updateBoardUser(BoardDto dto);
	
	public List<BoardDto> selectUserBoardDataAll(String writer);
	public List<BoardDto> selectUserBoardDataprev(String writer);
}
