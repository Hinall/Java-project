package com.digitalgis.utils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:utils.properties")
public class LoggerUtil {

	@Value("${logFilePath}")
	private String logFilePath;

	private static Logger log;

	public void setFileDetails() throws ParseException {
		// System.setProperty("current.date", new
		// SimpleDateFormat("dd-MM-yyyy").format(new Date()));
		Calendar beforeMonth = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		beforeMonth.add(Calendar.MONTH, -1);
		File file = new File(logFilePath);
		if (file != null && file.listFiles() != null && file.listFiles().length > 0) {
			for (int i = 0; i < file.listFiles().length; i++) {
				Date fileDate = simpleDateFormat
						.parse(new SimpleDateFormat("dd-MM-yyyy").format(file.listFiles()[i].lastModified()));
				Date todayDate = simpleDateFormat
						.parse(new SimpleDateFormat("dd-MM-yyyy").format(beforeMonth.getTime()).toString());

				if (fileDate.compareTo(todayDate) < 0) {
					file.listFiles()[i].delete();
				}
			}
		}
	}

	public static void setError(Class<?> class1, String error) {
		log = Logger.getLogger(class1);
		log.error(error);

	}

	public static void setInfo(Class<?> class1, String info) {
		log = Logger.getLogger(class1);
		log.info(info);
	}

	public static void setWarning(Class<?> class1, String warning) {
		log = Logger.getLogger(class1);
		log.warn(warning);
	}

}
