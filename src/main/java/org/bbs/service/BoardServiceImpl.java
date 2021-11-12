package org.bbs.service;

import java.util.List;

import org.bbs.mapper.BoardMapper;
import org.bbs.model.BoardDTO;
import org.bbs.page.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public void insert(BoardDTO dto) {
		boardMapper.insert(dto);
	}
	
	@Override
	public List<BoardDTO> selectByAll() {
		return boardMapper.selectByAll();
	}

	@Override
	public void delete(Long bno) {
		boardMapper.delete(bno);
		
	}

	@Override
	public BoardDTO boardView(Long bno) {
		
		return boardMapper.boardView(bno);
	}

	@Override
	public void update(BoardDTO dto) {
		boardMapper.update(dto);
		
	}

	@Override
	public List<BoardDTO> getListPaging(Criteria cri) {
		
		return boardMapper.getListPaging(cri);
	}

	@Override
	public int getTotal() {
		
		return boardMapper.getTotal();
	}

}
