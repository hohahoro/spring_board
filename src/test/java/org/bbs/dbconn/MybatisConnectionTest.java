package org.bbs.dbconn;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MybatisConnectionTest {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	
//	mybatis 연동 테스트
	@Test
	public void testMyBatis() {
		try (SqlSession session = sqlSessionFactory.openSession(); Connection conn = session.getConnection();) {

			log.info("session 확인>>>>>>" + session);
			log.info("Connection 확인>>>>>>>>" + conn);

		} catch (Exception e) {
			System.out.println("실패");
		}
	}

}
