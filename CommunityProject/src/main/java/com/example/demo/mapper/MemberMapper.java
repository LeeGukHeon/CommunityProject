package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.MemberDto;



@Mapper
public interface MemberMapper {
	// 기본적으로 mybatis를 쓸 때 객체의 이름의 통일성을 위해서
	// 또 메서드의 관리를 위해서
	// mapper 인터페이스를 만들어 주는게 좋다
	
	public int insertMemberData(MemberDto dto); //입력
	public MemberDto selectMemberDataOne(String id); //하나 가져오기
	public int updateMemberData(MemberDto dto); //수정
	public int deleteMemberData(String id); //삭제
	public List<MemberDto> selectMemberDataAll(); //전부 가져오기
	public int selectMemberDataCount(); //개수 가져오기
	public int matchPw(MemberDto dto); //매칭 패스워드
	public MemberDto getUserInfo(MemberDto dto); //이름 가져오기
	public int secession(String id); //탈퇴
	public String getUserAuth(MemberDto dto); //로그인 회원 권한 가져오기
	public int updateuserauth(@Param("auth")String auth, @Param("id")String id);
	public int duplicate(String id);
}
