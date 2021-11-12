package org.bbs.service;

import java.util.List;

import org.bbs.model.BoardDTO;
import org.bbs.page.Criteria;

public interface BoardService {
	
	public void insert(BoardDTO dto);
	
	public List<BoardDTO> selectByAll();
	
	public List<BoardDTO> getListPaging(Criteria cri);
	
	public int getTotal();

	public void delete(Long bno);

	public BoardDTO boardView(Long bno);

	public void update(BoardDTO dto);
	
}
