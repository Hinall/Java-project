package com.digitalgis.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.digitalgis.model.Response;
import com.digitalgis.service.CommonService;
import com.digitalgis.utils.CustomMessages;
import com.digitalgis.utils.Utility;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class CommonController {

	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

	@Autowired
	CommonService commonService;

	@Value("${image.FilePath}")
	private String imageFilePath;
	
	@Value("${image.CropImageFilePath}")
	private String cropImageFilePath;
	
	
	
	
	
	
	
	
	// WRITE A USED API ABOVE THIS COMMENTS
	
	

	@RequestMapping(value = "/citizen/getVisitorCounter", method = RequestMethod.GET)
	public ResponseEntity<String> getVisitorCounter() {
		try {
			String result = commonService.getVisitorCounter();
			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/citizen/updateVisitorCounter", method = RequestMethod.POST)
	public ResponseEntity<String> updateVisitorCounter(@RequestBody String json) {
		try {
			commonService.updateVisitorCounter(json);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/zoomToBBox", method = RequestMethod.POST)
	public ResponseEntity<String> zoomToBBox(@RequestBody String json, HttpServletRequest request) {
		try {
			String result = commonService.zoomToBBox(json);
			JSONObject jsonObj = new JSONObject(result);

			if (jsonObj.getInt("responseCode") == 200) {
				JSONArray jsonArr = jsonObj.getJSONArray("data");
				int data_length = jsonArr.length();

				for (int i = 0; i < data_length; i++) {
					JSONObject jsonObjSingle = jsonArr.getJSONObject(i);

					String bBox = jsonObjSingle.getString("bbox");
					bBox = bBox.replace("BOX", "");
					bBox = bBox.replace("(", "");
					bBox = bBox.replace(")", "");

					String bBox_arr[] = bBox.split(",");
					String point_x1 = bBox_arr[0].split(" ")[0];
					String point_y1 = bBox_arr[0].split(" ")[1];
					String point_x2 = bBox_arr[1].split(" ")[0];
					String point_y2 = bBox_arr[1].split(" ")[1];

					System.out.println("point x1 " + point_x1);
					System.out.println("point y1 " + point_y1);
					System.out.println("point x2 " + point_x2);
					System.out.println("point y2 " + point_y2);

					ArrayList<Double> lst = new ArrayList<Double>();
					lst.add(Double.valueOf(point_x1));
					lst.add(Double.valueOf(point_y1));
					lst.add(Double.valueOf(point_x2));
					lst.add(Double.valueOf(point_y2));

					System.out.println("---- " + lst);
					JSONObject newObj = new JSONObject();
					newObj.put("bbox_arr", lst);
					jsonArr.put(newObj);
					jsonObj.put("data", jsonArr);
				}
			}

			return ResponseEntity.ok(jsonObj.toString());
		} catch (Exception e) {
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}

	@RequestMapping(value = "/insertVisitorCount", method = RequestMethod.POST)
	public ResponseEntity<String> insertVisitorCounter(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession(false);
			// (request.getUserPrincipal().getName(), request.getRequestURI(),
			// request.getRemoteAddr(), "", "", true, false, request.getServerPort());

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("username", request.getUserPrincipal());
			jsonObject.put("url", request.getRequestURI());
			jsonObject.put("ip", request.getRemoteAddr());
			jsonObject.put("status", true);
			jsonObject.put("is_home_page_hit", true);

			String result = commonService.insertVisitorCounter(jsonObject.toString());
			return new ResponseEntity<String>(result, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/feedback", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> feedbackForm(@RequestParam(value = "images", required = false) MultipartFile[] images,
			@RequestParam(value = "jsonData", required = true) String dataJson, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		JSONObject obj = null;
		String result = "";
		StringBuilder imgFile = new StringBuilder();

		try {
			obj = new JSONObject(dataJson);
			HashSet<String> feedbackImg = new HashSet<String>();

			if (images != null) {
				for (MultipartFile m : images) {
					Utility.uploadImage(m, "feedback", m.getOriginalFilename(), imageFilePath);
					// imgFile.append(m.getOriginalFilename() + ",");
					feedbackImg.add(m.getOriginalFilename());
				}
			}

			if (feedbackImg.size() > 0) {
				for (String image : feedbackImg) {
					imgFile.append(image + ",");
				}
				if (imgFile != null && imgFile.length() > 0) {
					obj.put("image_name", imgFile.toString().substring(0, imgFile.length() - 1));
				}
			} else {
				obj.put("image_name", "");
			}

			result = commonService.addFeedbackData(obj.toString());
			return ResponseEntity.ok(result);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}

	@ApiIgnore
	@RequestMapping(value = "/get_list_of_feedback", method = RequestMethod.GET)
	public ResponseEntity<?> getWebAttributeCount(HttpServletRequest request) {
		try {
			String result = commonService.getListOfFeedback();
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}

	@RequestMapping(value = "/getcaptcha", method = RequestMethod.GET, produces = { MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE })
	public byte[] getCaptcha1(HttpServletRequest request) {

		final String FILE_TYPE = "jpeg";

		String captcha = generateCaptcha(6);
		try {

			JSONObject jsonObject = new JSONObject();

			jsonObject.put("flag", "create");
			jsonObject.put("captcha", captcha);
			jsonObject.put("url", request.getRemoteAddr());
			
			String result = commonService.getCaptcha(jsonObject.toString());
			
			//jdbcTemplate.queryForObject("SELECT fn_web_verify_captcha('" + jsonObject.toString() + "');", String.class);

			int width = 110, height = 30;
			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.OPAQUE);
			Graphics graphics = bufferedImage.createGraphics();
			graphics.setFont(new Font("Arial", Font.BOLD, 20));
			graphics.setColor(new Color(169, 169, 169));
			graphics.fillRect(0, 0, width, height);
			graphics.setColor(new Color(255, 255, 255));
			graphics.drawString(captcha, 20, 25);
			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, FILE_TYPE, arrayOutputStream);
			return arrayOutputStream.toByteArray();

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
			//return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}

	}

	public static String generateCaptcha(int captchaLength) {
		String captcha = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuffer captchaBuffer = new StringBuffer();
		Random random = new Random();
		while (captchaBuffer.length() < captchaLength) {
			int index = (int) (random.nextFloat() * captcha.length());
			captchaBuffer.append(captcha.substring(index, index + 1));
		}
		return captchaBuffer.toString();
	}

	@ApiIgnore
	@RequestMapping(value = "/verify_captcha", method = RequestMethod.POST)
	public ResponseEntity<?> verifyCaptcha(@RequestBody String jsonData,HttpServletRequest request) {
		try {
			String result = commonService.verify_captcha(jsonData);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}
	
	
	@RequestMapping(value = "/download_file/{fileType}/{fileName}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getPDF(@PathVariable("fileType") String fileType,@PathVariable("fileName") String file_name) throws IOException, JSONException {
		//JSONObject jsonObject = new JSONObject(json);
		//String file_name = jsonObject.getString("file_name");
		File file = new File(cropImageFilePath + File.separator + fileType + File.separator + file_name);
		byte[] contents = Files.readAllBytes(file.toPath());
		HttpHeaders headers = new HttpHeaders();
		//headers.setContentType(MediaType.MULTIPART_MIXED);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		//String filename = file_name + ".xlsx";
		//headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
		return response;
	}
	
	@ApiIgnore
	@RequestMapping(value = "/get_bookmark_by_userid", method = RequestMethod.POST)
	public ResponseEntity<?> getBookmarkByUserId(@RequestBody String json,HttpServletRequest request) {
		try {
			String result = commonService.getBookmarkByUserId(json);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}
	
	@ApiIgnore
	@RequestMapping(value = "/add_bookmark", method = RequestMethod.POST)
	public ResponseEntity<?> add_bookmark(@RequestBody String json,HttpServletRequest request) {
		try {
			String result = commonService.add_bookmark(json);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}
	
	@ApiIgnore
	@RequestMapping(value = "/getInformationofLayer", method = RequestMethod.POST)
	public ResponseEntity<?> getInformationofLayer(@RequestBody String json,HttpServletRequest request) {
		Response<String> respose = new Response<>();
		try {
			respose.setData(commonService.getInformationofLayer(json));
			respose.setStatus(true);
			respose.setStatusCode(200);
		} catch (Exception e) {
			e.printStackTrace();
			respose.setMessage(e.getMessage());
			respose.setStatus(false);
			respose.setStatusCode(401);
		}
		return new ResponseEntity<Response<String>>(respose, HttpStatus.ACCEPTED);
	}
	
}
