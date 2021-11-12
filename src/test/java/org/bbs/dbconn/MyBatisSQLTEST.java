package org.bbs.dbconn;

import org.bbs.mapper.TestMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MyBatisSQLTEST {
	
	@Autowired
	private TestMapper testMapper;
	
	@Test
	public void testSelect() {
		String nowTime = testMapper.getTime();
		log.info("=========================================================");
		log.info("myBatis testSelect nowTime 결과 = " + nowTime);
		log.info("=========================================================");
	}
	
}
