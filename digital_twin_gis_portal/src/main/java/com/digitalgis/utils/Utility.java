package com.digitalgis.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.digitalgis.config.DigitalTwinAPIException;

@PropertySource("classpath:application.properties")
public class Utility extends JdbcDaoSupport {

	private static final Logger logger = LoggerFactory.getLogger(Utility.class);

	public static final String[] property_image_pic_arr = new String[] { "jpeg", "jpg", "png" };
	public static final String[] property_image_mimeTypes = new String[] { "image/jpeg", "image/png",
			"application/octet-stream" };

//	@Autowired
//	private static Environment env;

	public static long storeFile(String imageFilePath, MultipartFile file) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		long isfileuploaded = 0;
		try {

			// Copy file to the target location (Replacing existing file with the same name)
			String targetLocation = imageFilePath + File.separator + fileName;
			File dir = new File(targetLocation);
			if (!dir.exists()) {
				if (dir.mkdirs()) {
					System.out.println("Folder Created");
				}
			} else {
				System.out.println("Folder Exists");
			}
			Path path = Paths.get(targetLocation);
			isfileuploaded = Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

			return isfileuploaded;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return isfileuploaded;
	}

	public static String generateRandomString(int len) {
		String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
		SecureRandom rnd = new SecureRandom();

		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));

		return sb.toString();
	}

	public static void deleteExistingImage(MultipartFile multipartFile, String fileUrl) {
		try {
			if (!multipartFile.isEmpty()) {
				Files.deleteIfExists(Paths.get(fileUrl + multipartFile.getOriginalFilename()));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * this method will use to upload property image to respected file path.
	 * 
	 * @param multipartFile
	 * @param folderName
	 * @param fileName
	 * @return
	 * @throws DigitalTwinAPIException
	 */
	public static String uploadImage(MultipartFile multipartFile, String folderName, String fileName,
			String imageFilePath) throws DigitalTwinAPIException {
		String fileUrl = "";
		try {
			logger.info("name " + multipartFile.getOriginalFilename());
			if (multipartFile.getContentType() != null) {

				logger.info("Content Type :: " + multipartFile.getContentType());
				boolean contentsType = Arrays.stream(property_image_mimeTypes)
						.anyMatch(multipartFile.getContentType()::equalsIgnoreCase);
				if (!contentsType) {
					throw new  DigitalTwinAPIException(ResponseCode.PROFILE_PIC_IMAGE_NOT_SUPPORTED.getCode(),
							ResponseCode.PROFILE_PIC_IMAGE_NOT_SUPPORTED.getMessage());
				}

				// Store image from same server
				String URL = imageFilePath + File.separator + folderName + File.separator;
				File f = new File(URL);
				if (!f.isDirectory()) {
					f.mkdir();
				}
				fileUrl = URL + fileName;
				boolean b = Arrays.stream(property_image_pic_arr)
						.anyMatch(FilenameUtils.getExtension(fileUrl)::equalsIgnoreCase);
				if (!b) {
					throw new DigitalTwinAPIException(ResponseCode.PROFILE_PIC_IMAGE_NOT_SUPPORTED.getCode(),
							ResponseCode.PROFILE_PIC_IMAGE_NOT_SUPPORTED.getMessage());
				}
				logger.info("File Path :" + fileUrl);
				File file = new File(fileUrl);
				file.createNewFile();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
				stream.write(multipartFile.getBytes());
				stream.close();

				return fileUrl;

			} else {
				return fileUrl;
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new DigitalTwinAPIException(ResponseCode.UNABLE_TO_FILE_UPLOAD.getCode(),
					ResponseCode.UNABLE_TO_FILE_UPLOAD.getMessage());
		}
	}

	public static String getRandomNumberString() {
		// It will generate 6 digit random Number.
		Random rnd = new Random();
		int number = rnd.nextInt(999999);
		return String.format("%06d", number);
	}

	public static boolean sendOTP(String mobileNo, String OTP, String APIUrl, String username, String apikey,
			String senderid, String templateid, String msg1, String msg2) {

//		String username = "ASCDCL";
// 		String apikey = "c01f32640f54e44f7660";
//		String senderid = "AMCGOV";
//		String templateid = "1707164421981494371";
//		String message = "Dear Customer, Your OTP for Survey Property is "+ OTP + ". Use this OTP to Validate, Thank you. Regards, AMCORP";
		String message = msg1 + " " + OTP + msg2;

		try {
			String encoding = "UTF-8";
			String queryString = "username=" + URLEncoder.encode(username, encoding) + "&apikey="
					+ URLEncoder.encode(apikey, encoding) + "&senderid=" + URLEncoder.encode(senderid, encoding)
					+ "&templateid=" + URLEncoder.encode(templateid, encoding) + "&mobile="
					+ URLEncoder.encode(mobileNo, encoding) + "&message=" + URLEncoder.encode(message, encoding);

			// Send request to the API servers over HTTPS
			// URL url = new URL("http://smsatm.net/v3/api.php?");
			URL url = new URL(APIUrl);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);

			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(queryString);
			wr.flush();

			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String result = rd.readLine();
			wr.close();
			rd.close();

			if (result == null) {
				System.out.println("No response received");
				return false;
			} else if (result.contains("campid")) {
				System.out.println("Message sent successfully");
			} else {
				System.out.println("Error - " + result);
				return false;
			}
		} catch (Exception e) {
			System.out.println("Error - " + e);
		}

		return true;
	}

}
