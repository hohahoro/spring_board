package org.bbs.boardtest;

import java.util.List;

import org.apache.catalina.mapper.Mapper;
import org.bbs.mapper.BoardMapper;
import org.bbs.model.BoardDTO;
import org.bbs.page.Criteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTest {
	@Autowired
	private BoardMapper boardMapper;

//	@Test public void boardInsertTest() {

//	 BoardDTO dto = new BoardDTO(); dto.setBtitle("테스트제목999");
//	 dto.setBcontent("테스트내용999"); dto.setBwriter("테스터999");
	 
//	 boardMapper.insert(dto);
//	 
//	 log.info("==============boardInsertTest 확인==============="); log.info(dto);
//	 log.info("================================================");
//			for(int i = 0; i<1000; i++) {
//				BoardDTO dto = new BoardDTO();
//				dto.setBtitle(i+"번 제목");
//				dto.setBcontent(i+"번 내용");
//				dto.setBwriter(i+"번 작성자");
//				
//				boardMapper.insert(dto);
	//	}
//	 }

//	 @Test public void selectByAllTest(){ 	 
//		 boardMapper.selectByAll().forEach(i ->log.info(i)); 
//	 }

//	 @Test public void deleteTest(){ 
//		 boardMapper.delete(1006);
//	 }

//	@Test
//	public void boardView() {
//		boardMapper.boardView(1L);
//	}
	
//	@Test
//	public void boardUpdateTest() {
//		BoardDTO dto = new BoardDTO();
//		dto.setBno(1L);
//		dto.setBtitle("수정된 제목");
//		dto.setBcontent("수정된 내용");
//		
//		boardMapper.update(dto);
//		
//	}
	
	@Test
	public void boardPageTest() {
		Criteria cri = new Criteria();
		cri.setPageNum(20);
		List<BoardDTO> list = boardMapper.getListPaging(cri);
		list.forEach(i -> System.out.println(i));
	}
	
}
