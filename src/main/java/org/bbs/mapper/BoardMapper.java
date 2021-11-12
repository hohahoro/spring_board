package org.bbs.mapper;

import java.util.List;

import org.bbs.model.BoardDTO;
import org.bbs.page.Criteria;

public interface BoardMapper {
	
	public void insert(BoardDTO dto);
	
	public List<BoardDTO> selectByAll();
	
	public List<BoardDTO> getListPaging(Criteria cri);
	
	public int getTotal();
	
	public BoardDTO boardView(Long bno);	
	
	public void delete(long dno);
	
	public void update();

	public void update(BoardDTO dto);
	
	
}
