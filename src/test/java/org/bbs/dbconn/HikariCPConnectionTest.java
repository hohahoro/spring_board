package org.bbs.dbconn;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class HikariCPConnectionTest {
	
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void testConnection() {
		
		try(Connection conn = dataSource.getConnection()) {
			
			log.info("성공"+conn);
			
			
		}catch (Exception e) {
			System.out.println("몬가 문제다.");
		}
		
	}
}
