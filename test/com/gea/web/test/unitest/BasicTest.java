package com.gea.web.test.unitest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.gea.web.dao.LecturaDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:WebContent/WEB-INF/GEAWeb-servlet.xml")
public class BasicTest {
	
	@Autowired
	LecturaDAO lecturaDAO;

	
	@Test
    public void testAssertions() {
		assertTrue(true);
    }
}
