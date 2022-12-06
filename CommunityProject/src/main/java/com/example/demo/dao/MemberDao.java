package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.MemberDto;

import com.example.demo.mapper.MemberMapper;

@Repository
public class MemberDao implements MemberMapper{

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public int insertMemberData(MemberDto dto) {
		// TODO Auto-generated method stub
		return memberMapper.insertMemberData(dto);
	}

	@Override
	public MemberDto selectMemberDataOne(String id) {
		// TODO Auto-generated method stub
		return memberMapper.selectMemberDataOne(id);
	}

	@Override
	public int updateMemberData(MemberDto dto) {
		// TODO Auto-generated method stub
		return memberMapper.updateMemberData(dto);
	}

	@Override
	public int deleteMemberData(String id) {
		// TODO Auto-generated method stub
		return memberMapper.deleteMemberData(id);
	}

	@Override
	public List<MemberDto> selectMemberDataAll() {
		// TODO Auto-generated method stub
		return memberMapper.selectMemberDataAll();
	}

	@Override
	public int selectMemberDataCount() {
		// TODO Auto-generated method stub
		return memberMapper.selectMemberDataCount();
	}
	@Override
	public int matchPw(MemberDto dto) {
		// TODO Auto-generated method stub
		return memberMapper.matchPw(dto);
	}

	@Override
	public MemberDto getUserInfo(MemberDto dto) {
		// TODO Auto-generated method stub
		return memberMapper.getUserInfo(dto);
	}

	@Override
	public int secession(String id) {
		// TODO Auto-generated method stub
		return memberMapper.secession(id);
	}

	@Override
	public String getUserAuth(MemberDto dto) {
		// TODO Auto-generated method stub
		return memberMapper.getUserAuth(dto);
	}

	@Override
	public int updateuserauth(String auth, String id) {
		// TODO Auto-generated method stub
		return memberMapper.updateuserauth(auth, id);
	}

	@Override
	public int duplicate(String id) {
		// TODO Auto-generated method stub
		return memberMapper.duplicate(id);
	}


}
