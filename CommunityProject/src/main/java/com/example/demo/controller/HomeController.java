package com.example.demo.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.BoardDao;
import com.example.demo.dao.CommentDao;
import com.example.demo.dao.MemberDao;
import com.example.demo.dto.BoardDto;
import com.example.demo.dto.CommentDto;
import com.example.demo.dto.MemberDto;

@Controller
public class HomeController {
	
	@Autowired
	MemberDao mdao;
	
	@Autowired
	BoardDao bdao;
	
	@Autowired
	CommentDao cdao;
	
/////////////////////////// 로그인 ////////////////////////
	
	@PostMapping("/loginProc")
	public String loginProc(MemberDto mDto, HttpSession session, Model model) {
		String auth = mdao.getUserAuth(mDto);
		if(mdao.matchPw(mDto) > 0) {
			if(auth.equals("withdrawal")) {
				return "notuser";
			}else {
				session.setAttribute("login", mdao.getUserInfo(mDto));
				return "redirect:/main";
			}
		}else {
			return "loginFailed";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("login");
		return "redirect:/";
	}
	
/////////////////////////// 로그인 ////////////////////////
	
	
/////////////////////////// 회원가입 ////////////////////////
	
	@GetMapping("/regist")
	public String regist() {
		return "regist";
	}
	
	@PostMapping("/registProc")
	public String registProc(MemberDto dto) {
		mdao.insertMemberData(dto);
		return "redirect:/";
	}
	@RequestMapping("/duplicate")
	@ResponseBody
	public int duplicate(@RequestParam Map<String, Object> param){
		String id = (String) param.get("id");
		int result = mdao.duplicate(id);
		return result;
	}

	
///////////////////////// 회원가입 ////////////////////////
	
	//메인
	@GetMapping({"/","/main"})
		public String main(Model model, Integer page, Integer offset, HttpSession session) {
				page = 1;
				offset = 10;
			//갤러리 구분없이 최신글 목록
			List<BoardDto> list = bdao.selectBoardDataAll(offset);
			//최근 가장 글이 많이 올라온 갤러리명
			String boardName = bdao.recentPopular();
			if(session.getAttribute("login") != null) {
				MemberDto list3 = (MemberDto) session.getAttribute("login");
				String id = list3.getId();
				int count = bdao.selectUserBoardCount(id);
				model.addAttribute("count", count);
			}
			
			List<BoardDto> list2 = bdao.boardPaging((page-1)*offset, boardName, offset);
			
			if(!list2.isEmpty()) {
				model.addAttribute("recentPopular",list2);
			}else {
				model.addAttribute("recentPopular", null);
			}
			model.addAttribute("boardData", list);
			
			return "main";
	}
	//메인
	
///////////////////////// 게시판 ////////////////////////
	
	@GetMapping("/board")
	public String loginMain(Model model, Integer page,Integer offset, String boardtitle) {
		if(page == null) {
			page = 1;
		}
		//전체 글 수
		int totalView = bdao.selectBoardDataCount(boardtitle);
		//한 페이지에 보여줄 글 수
		offset = 10;
		//페이지 수
		int pageCount = (int) Math.ceil((float)totalView/offset);
		List<BoardDto> list = bdao.boardPaging((page-1)*offset, boardtitle, offset);
		model.addAttribute("boardData", list);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("currentPage", page);
		return "board";
	}
	
	@GetMapping("/write")
	public String write(HttpServletRequest req, Model model, HttpSession session) {
		MemberDto mdto = (MemberDto) session.getAttribute("login");
		if(mdto == null) {
			return "noAuth";
		}else if(req.getParameter("boardtitle") == null) {
			return "./error/403";
		}else {
			model.addAttribute("boardtitle", req.getParameter("boardtitle"));
			return "write";
		}
	}
	@PostMapping("/writeProc")
	public String writeProc(BoardDto dto) {
		bdao.insertBoardData(dto);
		
		return "redirect:/board?boardtitle="+dto.getBoardtitle();
	}
	@GetMapping("/showBoard")
	public String writeProc(int idx, Model model, CommentDto dto ) {
		BoardDto data = bdao.selectBoardDataOne(idx);
		List<CommentDto> commentData = cdao.showAllComment(idx);
		int cCount = cdao.getCommentCount(idx);
		bdao.updateViews(idx);
		model.addAttribute("commentCount", cCount);
		model.addAttribute("data", data);
		model.addAttribute("commentData", commentData);
		
		return "showBoard";
	}
	@GetMapping("/updateBoard")
	public String updateBoard(int idx, Model model) {
		BoardDto data = bdao.selectBoardDataOne(idx);
		model.addAttribute("data", data);
		return "updateBoard";
	}
	@PostMapping("/updateProc")
	public String updateProc(BoardDto dto) {
		bdao.updateBoardData(dto);
		return "redirect:/showBoard?boardtitle="+dto.getBoardtitle()+"&idx="+dto.getIdx();
	}
	@GetMapping("/deleteBoard")
	public String deleteBoard(int idx) {
		bdao.deleteBoardData(idx);
		cdao.deleteAllComment(idx);
		return "redirect:/main";
	}
	@GetMapping("/search")
	public String searchBoard(Model model,Integer page,Integer offset, String searchOption, String searchValue, HttpServletRequest req) {
		String option = (req.getParameter("searchOption"));
		String value = (req.getParameter("searchValue"));
		if(page == null) {
			page = 1;
		}
		//전체 글 수
		int totalView = bdao.searchBoardDataCount(searchOption, searchValue);
		//한 페이지에 보여줄 글 수
		offset = 10;
		//페이지 수
		int pageCount = (int) Math.ceil((float)totalView/offset);
		List<BoardDto> list = bdao.searchBoardData(searchOption, searchValue, (page-1)*offset);
		model.addAttribute("boardData", list);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("searchOption", option);
		model.addAttribute("searchValue", value);
		model.addAttribute("currentPage", page);
		return "search";
	}
	
///////////////////////// 게시판 ////////////////////////
	

	//댓글달기
	
	@PostMapping("/addcomment")
	public String addcomment(CommentDto dto, HttpServletRequest request) {
		cdao.insertComment(dto);
		int cCount = dto.getbIdx();
		bdao.updateCCount(cCount);
		return "redirect:" + request.getHeader("Referer");
	}
	@PostMapping("/deletecomment")
	public String deletecomment(CommentDto dto, int idx, HttpServletRequest request) {
		cdao.deleteSelectedComment(idx);
		int cCount = dto.getbIdx();
		bdao.minusCCount(cCount);
		return "redirect:" + request.getHeader("Referer");
	}
///////////////////////// 관리자 ////////////////////////
	
	@GetMapping("/admin")
	public String admin(Model model, HttpSession session, MemberDto dto) {
		MemberDto mdto = (MemberDto) session.getAttribute("login");
		if(mdto == null) {
			return "./error/401";
		}else if(!mdto.getAuth().equals("admin")) {
			return "./error/403";
		}else{
			List<MemberDto> data = mdao.selectMemberDataAll();
			model.addAttribute("memberData", data);
			return "admin";
		}
		
	}
	@GetMapping("/admin/deleteuser")
	public String deleteuser(HttpServletRequest req,  HttpSession session, MemberDto dto) {
		MemberDto mdto = (MemberDto) session.getAttribute("login");
		if(mdto == null) {
			return "./error/401";
		}else if(!mdto.getAuth().equals("admin")) {
			return "./error/403";
		}else {
			String id = req.getParameter("id");
			mdao.secession(id);
			return "redirect:/admin";
		}
	}
	@GetMapping("/admin/updateuserauth")
	public String updateuserauth(HttpServletRequest req, HttpSession session, MemberDto dto) {
		MemberDto mdto = (MemberDto) session.getAttribute("login");
		if(mdto == null) {
			return "./error/401";
		}else if(!mdto.getAuth().equals("admin")) {
			return "./error/403";
		}else {
			String id = req.getParameter("id");
			String auth = req.getParameter("auth");
			mdao.updateuserauth(auth, id);
			return "redirect:/admin";
		}
	}

///////////////////////// 마이페이지 //////////////////////
	
	@GetMapping("/mypage")
	public String mypage(Model model, HttpSession session, MemberDto dto) {
		MemberDto mdto = (MemberDto) session.getAttribute("login");
		if(mdto == null) {
			return "./error/401";
		}else {
			List<BoardDto> list = bdao.selectUserBoardDataprev(mdto.getId());
			model.addAttribute("list",list);
			return "mypage";
		}
	}
	
	@GetMapping("/mycontentlist")
	public String mycontentlist(Model model, HttpSession session, MemberDto dto) {
		MemberDto mdto = (MemberDto) session.getAttribute("login");
		if(mdto == null) {
			return "./error/401";
		}else {
			List<BoardDto> list = bdao.selectUserBoardDataAll(mdto.getId());
			int count = bdao.selectUserBoardCount(mdto.getId());
			model.addAttribute("count", count);
			model.addAttribute("list",list);
			return "mycontentlist";
		}
	}
	@GetMapping("/updateuser")
	public String updateuser(Model model, HttpSession session, MemberDto dto) {
		
		MemberDto mdto = (MemberDto) session.getAttribute("login");
		if(mdto == null) {
			return "./error/401";
		}else {
			MemberDto mdto2 = mdao.selectMemberDataOne(mdto.getId());
			model.addAttribute("data", mdto2);
			return "updateuser";
		}
	}
	@PostMapping("/updateuserProc")
	public String updateuserProc(MemberDto dto, HttpSession session) {
		mdao.updateMemberData(dto);
		session.setAttribute("login", mdao.getUserInfo(dto));
		return "redirect:/mypage";
	}
	
//탈퇴//
	@GetMapping("/secession")
	public String secession(MemberDto dto, HttpSession session) {
		MemberDto mdto = (MemberDto) session.getAttribute("login");
		mdao.secession(mdto.getId());
		session.removeAttribute("login");
		return "redirect:/main";
	}
	
}
