package org.bbs.controller;

import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.bbs.mapper.BoardMapper;
import org.bbs.model.BoardDTO;
import org.bbs.page.Criteria;
import org.bbs.page.ViewPage;
import org.bbs.service.BoardService;
import org.bbs.service.BoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	@Autowired
	public BoardService boardService;
	
	@GetMapping("/")
	public String home() {		
		
		return "home";
	}
	
	@GetMapping("/register")
	public String goregister() {		
		
		return "register";
	}
	
	
	@PostMapping("/registerForm")
	public String insert(BoardDTO dto) {
		boardService.insert(dto);
		
		return "redirect:/";
	}
	
	@GetMapping("/boardList")
	public String boardList(Model model, Criteria cri) {
		List<BoardDTO> boardList = boardService.getListPaging(cri);
		model.addAttribute("boardList", boardList);
		
		int total = boardService.getTotal();
		ViewPage vp = new ViewPage(cri, total);
		model.addAttribute("pageMaker", vp);
		
		return "boardList";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("bno")String ubno) {
		Long bno = Long.parseLong(ubno);
		boardService.delete(bno);
		
		return "redirect:/boardList";
	}
	
	@GetMapping("/boardView")
	public String boardView(@RequestParam("bno")String ubno, Model model) {
		Long bno = Long.parseLong(ubno);
		
		BoardDTO boardView = boardService.boardView(bno);
		model.addAttribute("boardView", boardView);
		
		return "boardView";
	}
	
	@GetMapping("/modifyForm")
	public String modifyForm(@RequestParam("bno") String ubno, Model model) {
		Long bno = Long.parseLong(ubno);
		
		BoardDTO dto = boardService.boardView(bno);
		model.addAttribute("dto", dto);	
		
		
		return "modify";
	}
	
	@PostMapping("/modify")
	public String modify(BoardDTO dto, @RequestParam("bno") String ubno) {
		
		boardService.update(dto);
		
		return "redirect:/boardView?bno="+ubno;
	}
}
