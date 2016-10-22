package playground.test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;


public class TestJavaTime {
	
	public static final Logger logger = LogManager.getLogger(TestJavaTime.class);
	
	@Test
	public void test1(){
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime now = ZonedDateTime.now(zoneId);
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ss");
		ZonedDateTime later = now.plus(100, ChronoUnit.SECONDS);
		logger.info(String.format("%s,zone:%s", df.format(now), zoneId.getId()));
		logger.info(String.format("%s,zone:%s", df.format(later), zoneId.getId()));
	}

}
