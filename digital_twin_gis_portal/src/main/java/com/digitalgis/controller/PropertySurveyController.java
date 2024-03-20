package com.digitalgis.controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.digitalgis.jwt.JwtProvider;
import com.digitalgis.service.CommonService;
import com.digitalgis.service.PropertySurveyService;
import com.digitalgis.utils.CustomMessages;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@EnableScheduling
public class PropertySurveyController {

	private static final Logger logger = LoggerFactory.getLogger(PropertySurveyController.class);

	@Autowired
	private PropertySurveyService propertySurveyService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private JwtProvider tokenProvider;

	@Value("${image.FilePath}")
	private String imageFilePath;

	@Value("${service.serviceURL}")
	private String serviceURL;
	
	@Value("${image.CropImageFilePath}")
	private String cropImageFilePath;
	
	@Autowired
	private Environment environment;
	
	
	
	
	
	// WRITE A USED API ABOVE THIS COMMENTS
	
	
	
	
	
	
	
	
	
	
	

	@ApiIgnore
	@RequestMapping(value = "/get_all_layers", method = RequestMethod.GET)
	public ResponseEntity<?> getAllLayers(HttpServletRequest request) {
		try {
			String result = propertySurveyService.getAllLayers();
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}

	@ApiIgnore
	@RequestMapping(value = "/get_verify_feature_data", method = RequestMethod.POST)
	public String getVerifyFeatureData(@RequestBody String json, HttpServletRequest httpServletRequest) {

		String hostUrl = httpServletRequest.getScheme() + "://" + httpServletRequest.getServerName() + ":"
				+ httpServletRequest.getServerPort() + httpServletRequest.getContextPath();

		String result = null;
		try {
			result = propertySurveyService.getVerifyFeatureData(json);
			JSONObject resultObj = new JSONObject(result);
			JSONArray jsonArray = new JSONArray();

//			dataMainJson = resultObj.getJSONArray("data").getJSONObject(0);
//			JSONArray jsonArray = new JSONArray();

			if (resultObj.getJSONArray("data").length() > 0) {
				for (int i = 0; i < resultObj.getJSONArray("data").length(); i++) {
					JSONObject obj1 = resultObj.getJSONArray("data").getJSONObject(i);
					// Image
					if (obj1.length() > 0) {
						if (!obj1.getString("property_image").equalsIgnoreCase("")) {
							JSONArray propertyImageArray = new JSONArray();
							if (obj1.getString("property_image").contains(",")) { // If Multiple image
																					// in database
								String[] propertyImages = obj1.getString("property_image").split(",");
								for (String imageProperty : propertyImages) {
									JSONObject jsonObject = new JSONObject();
									jsonObject.put("property_image",
											hostUrl + "/getImage/propertyImage/" + imageProperty);
									propertyImageArray.put(jsonObject);
								}

							} else { // IF Single image in database
								JSONObject jsonObject = new JSONObject();
								jsonObject.put("propety_image",
										hostUrl + "/getImage/propertyImage/" + obj1.getString("property_image"));
								propertyImageArray.put(jsonObject);
							}
							obj1.put("property_image", propertyImageArray);
						} else {
							JSONArray propertyImageArray = new JSONArray();
							obj1.put("property_image", propertyImageArray);
						}
					}
					jsonArray.put(obj1);

				}

				JSONObject resultMainObj = new JSONObject();
				resultMainObj.put("responseCode", 200);
				resultMainObj.put("responseMessage", resultObj.getString("responseMessage"));
				resultMainObj.put("data", jsonArray);
				result = resultMainObj.toString();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	@ApiIgnore
	@RequestMapping(path = "/getImage/{imagetype}/{imgname}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getPropertyImage(@PathVariable("imagetype") String imageType,
			@PathVariable("imgname") String imgname, HttpServletResponse response) throws IOException {

		byte[] pdfContents = null;

		// Fetch image from same server
		String fp = imageFilePath + "/" + imageType + "/" + imgname;
		Path path = Paths.get(fp);
		// byte[] pdfContents = null;
		try {
			pdfContents = Files.readAllBytes(path);

		} catch (IOException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("image/png"));
		String filename1 = imgname;
		headers.add("Content-Disposition", "inline;filename=" + filename1);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		return new ResponseEntity<byte[]>(pdfContents, headers, HttpStatus.OK);
	}

	@ApiIgnore
	@RequestMapping(value = "/draw_feature_data", method = RequestMethod.POST)
	public ResponseEntity<?> drawFeatureData(@RequestBody String json, HttpServletRequest request) {
		try {
			String result = propertySurveyService.drawFeatureData(json);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}

	@ApiIgnore
	@RequestMapping(value = "/get_draw_feature_data", method = RequestMethod.POST)
	public ResponseEntity<?> getDrawFeatureData(@RequestBody String json, HttpServletRequest request) {
		try {
			String result = propertySurveyService.getDrawFeatureData(json);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}

	@ApiIgnore
	@RequestMapping(value = "/get_ward_data", method = RequestMethod.GET)
	public ResponseEntity<?> getWardData(HttpServletRequest request) {
		try {
			String result = propertySurveyService.getWardData();
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}

	@ApiIgnore
	@RequestMapping(value = "/get_survey_details", method = RequestMethod.POST)
	public ResponseEntity<String> getSurveyDetails(@RequestBody String json, HttpServletRequest httpServletRequest) {
		try {
			String result = propertySurveyService.getSurveyDetails(json);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}

	@ApiIgnore
	@RequestMapping(value = "/get_survey_details_by_id", method = RequestMethod.POST)
	public ResponseEntity<String> getSurveyDetailsById(@RequestBody String json,
			HttpServletRequest httpServletRequest) {
		try {
			String result = propertySurveyService.getSurveyDetailsById(json);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}

	@ApiIgnore
	@RequestMapping(value = "/get_web_parent_all_layer", method = RequestMethod.GET)
	public ResponseEntity<?> getWebParentLayer(HttpServletRequest request) {
		try {
			String result = propertySurveyService.getWebParentLayer();
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}

	@ApiIgnore
	@RequestMapping(value = "/get_web_all_layer", method = RequestMethod.GET)
	public ResponseEntity<?> getWebAllLayer(HttpServletRequest request) {
		try {
			String result = propertySurveyService.getWebAllLayer();
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}

	@ApiIgnore
	@RequestMapping(value = "/get_prabhag_by_ward", method = RequestMethod.POST)
	public ResponseEntity<?> getPrabhagByWard(@RequestBody String json, HttpServletRequest request) {
		try {
			String result = propertySurveyService.getPrabhagByWard(json);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}

	@ApiIgnore
	@RequestMapping(value = "/get_sqkm_by_ward", method = RequestMethod.POST)
	public ResponseEntity<?> getSqkmByWard(@RequestBody String json, HttpServletRequest request) {
		try {
			String result = propertySurveyService.getSqkmByWard(json);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}

	@ApiIgnore
	@RequestMapping(value = "/get_all_layer_and_image", method = RequestMethod.POST)
	public ResponseEntity<?> getWebLayerAndImage(@RequestBody String json,HttpServletRequest request) {
		try {
			String result = propertySurveyService.getWebLayerAndImage(json);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}

	@ApiIgnore
	@RequestMapping(value = "/get_master_data_by_key", method = RequestMethod.POST)
	public ResponseEntity<?> getMasterDataByKey(@RequestBody String json, HttpServletRequest request) {
		try {
			String result = propertySurveyService.getMasterDataByKey(json);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}

	@ApiIgnore
	@RequestMapping(value = "/get_dynamic_chart_data", method = RequestMethod.POST)
	public ResponseEntity<?> getDynamicChartData(@RequestBody String json, HttpServletRequest request) {
		try {
			String result = propertySurveyService.getDynamicChartData(json);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}

	@ApiIgnore
	@RequestMapping(value = "/get_bar_chart_data", method = RequestMethod.POST)
	public ResponseEntity<?> get_bar_chart_data(@RequestBody String json, HttpServletRequest request) {
		try {
			String result = propertySurveyService.getBarChartData(json);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}

	@ApiIgnore
	@RequestMapping(value = "/get_web_swipe_layer", method = RequestMethod.POST)
	public ResponseEntity<?> getSwipeLayerData(@RequestBody String json, HttpServletRequest request) {
		try {
			String result = propertySurveyService.getSwipeLayerData(json);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}

	@ApiIgnore
	@RequestMapping(value = "/get_web_change_analysis_layer", method = RequestMethod.POST)
	public ResponseEntity<?> getChangeAnalysisLayerData(@RequestBody String json, HttpServletRequest request) {
		try {
			String result = propertySurveyService.getChangeAnalysisLayerData(json);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}

	@ApiIgnore
	@RequestMapping(value = "/get_web_attribute_count", method = RequestMethod.POST)
	public ResponseEntity<?> getWebAttributeCount(@RequestBody String json, HttpServletRequest request) {
		try {
			String result = propertySurveyService.getWebAttributeCount(json);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}

	@ApiIgnore
	@RequestMapping(value = "/get_web_change_analysis_data", method = RequestMethod.POST)
	public ResponseEntity<?> getWebGetChangeAnalysisImage(@RequestBody String reqJson,
			HttpServletRequest httpServletRequest) {
		try {
			String result = "";

			JSONObject jsonObj = new JSONObject(reqJson);

			String from_year = jsonObj.getString("from_year");
			String to_year = jsonObj.getString("to_year");
			String ward = jsonObj.getString("ward");
			String prabhag = jsonObj.getString("prabhag");
			String sq_km = jsonObj.getString("sq_km");
			String aoi = jsonObj.getString("aoi");

			String aoi_type = "";
			String aoi_name = "";

			if (!ward.equals("")) {
				aoi_type = "ward";
				aoi_name = ward;
			}
			if (!prabhag.equals("")) {
				aoi_type = "prabhag";
				aoi_name = prabhag;
			}
			if (!sq_km.equals("")) {
				aoi_type = "sq_km";
				aoi_name = sq_km;
			}
			if (!aoi.equals("")) {
				aoi_type = "aoi";
				aoi_name = aoi;
			}

			if (from_year != "" && from_year != null && to_year != "" && to_year != null
					&& !jsonObj.isNull("geo_json")) {
				JSONObject geo_json = jsonObj.getJSONObject("geo_json");

				JSONObject final_web_geometry = null;
				System.out.println("geo_json " + geo_json);
				if (geo_json != null) {
					System.out.println("Web Draw geo_json  " + geo_json);
					JSONObject featureJson = geo_json.getJSONArray("features").getJSONObject(0);
					final_web_geometry = featureJson.getJSONObject("geometry");
					System.out.println("final_web_geometry " + final_web_geometry.toString());
				}

				/* PYTHON SERVICE CALL START */
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				RestTemplate restTemplate = new RestTemplate();

				JSONObject mainObj = new JSONObject();
				JSONObject geojson_content = new JSONObject();
				JSONObject crs = new JSONObject();
				JSONObject properties = new JSONObject();
				JSONArray features = new JSONArray();
				// JSONObject geometry = new JSONObject("{ \"type\": \"Polygon\",
				// \"coordinates\": [ [ [ 274521.42153208784, 2114257.5493189734 ], [
				// 282211.41427834367, 2112967.8171423357 ], [ 274687.06988951203,
				// 2108109.1870772173 ], [ 274521.42153208784, 2114257.5493189734 ] ] ] }");
				JSONObject geometry = new JSONObject(final_web_geometry.toString());

				mainObj.put("from_year", from_year);
				mainObj.put("to_year", to_year);
				mainObj.put("aoi_type", aoi_type);
				mainObj.put("aoi_name", aoi_name);
				geojson_content.put("type", "FeatureCollection");
				crs.put("type", "name");
				properties.put("name", "urn:ogc:def:crs:EPSG::3857");
				crs.put("properties", properties);
				geojson_content.put("crs", crs);

				JSONObject feature_type = new JSONObject();
				JSONObject feature_properties = new JSONObject();
				feature_type.put("type", "Feature");

				feature_type.put("geometry", geometry);

				feature_properties.put("Name", "A");
				feature_type.put("properties", feature_properties);
				features.put(feature_type);
				geojson_content.put("features", features);
				mainObj.put("geojson_content", geojson_content);

				System.out.println("Main Obj = " + mainObj.toString());

				HttpEntity<String> entity = new HttpEntity<String>(mainObj.toString(), headers);
				result = restTemplate.postForObject(serviceURL, entity, String.class);

				/* PYTHON SERVICE CALL END */
			}

			String api_result = propertySurveyService.getWebGetChangeAnalysisImage(reqJson.toString());
			JSONObject mainResponse = new JSONObject(api_result);

			if (result != "" && result != null) {
				JSONObject pythonResponse = new JSONObject(result);
				mainResponse.put("change_analysis", pythonResponse);
			}
			System.out.println("Wait for Response");
			Thread.sleep(70000);
			// Thread.sleep(10000);
			System.out.println("Response ..");
			return ResponseEntity.ok(mainResponse.toString());

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}

	@ApiIgnore
	@RequestMapping(value = "/delete_draw_data", method = RequestMethod.POST)
	public ResponseEntity<?> deleteDrawData(@RequestBody String json, HttpServletRequest request) {
		try {
			String result = propertySurveyService.deleteDrawData(json);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}

	@ApiIgnore
	@RequestMapping(value = "/get_layer_data_by_layername", method = RequestMethod.POST)
	public ResponseEntity<?> getLayerDataByLayername(@RequestBody String json, HttpServletRequest request) {
		try {
			String result = propertySurveyService.getLayerDataByLayername(json);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}

	@ApiIgnore
	@RequestMapping(value = "/get_change_analysis_report", method = RequestMethod.POST)
	public ResponseEntity<String> getChangeAnalysisReport(@RequestBody String reqJson, HttpServletRequest request) {
		// String result="";
		try {
			
			JSONParser jsonParser = new JSONParser();
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject(reqJson);

			int from_year = Integer.parseInt(jsonObject.get("from_year").toString());
			int to_year = Integer.parseInt(jsonObject.get("to_year").toString());
			String ward_name = jsonObject.get("ward").toString();
			String prabhag = jsonObject.get("prabhag").toString();
			String sq_km = jsonObject.get("sq_km").toString();
			String file_name;

			if (!sq_km.equals("")) {
				String fileName = cropImageFilePath + "/" + "sq_km" + "/" + "sq_km_" + from_year + "_" + to_year + "_" + sq_km + ".json";
				FileReader fileReader = new FileReader(fileName);
				jsonArray.put(jsonParser.parse(fileReader));
				jsonObject.put("data", jsonArray);
				return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
			} else if (!prabhag.equals("") && !prabhag.equals(null)) {
				String fileName = cropImageFilePath + "/" + "prabhag" + "/" + "prabhag_" + from_year + "_" + to_year + "_" + prabhag + ".json";
				FileReader fileReader = new FileReader(fileName);
				jsonArray.put(jsonParser.parse(fileReader));
				jsonObject.put("data", jsonArray);
				return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
			} else if (!ward_name.equals("")) {
				String fileName = cropImageFilePath + "/" + "ward" + "/" + "ward_" + from_year + "_" + to_year + "_" + ward_name + ".json";
				FileReader fileReader = new FileReader(fileName);
				jsonArray.put(jsonParser.parse(fileReader));
				jsonObject.put("data", jsonArray);
				return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
			} else {
				File directoryPath = new File(cropImageFilePath + "/" + "ward");
				File filesList[] = directoryPath.listFiles();
				JSONArray fileNameArray = new JSONArray();
				for (int i = 0; i < filesList.length; i++) {
					File file = filesList[i];
					FileReader fileReader = new FileReader(cropImageFilePath + "/" + "ward" + "/" + file.getName());
					// jsonData += jsonParser.parse(fileReader).toString();
					file_name = file.getName();
					String pattern = "ward_" + from_year + "_" + to_year + ".*\\.json";
					if (file_name.matches(pattern)) {
						file_name = file_name.replace(".json", "");
						jsonArray.put(jsonParser.parse(fileReader));
						fileNameArray.put(file_name);
					}
				}
				System.out.println(fileNameArray);
				jsonObject.put("data", jsonArray);
				jsonObject.put("ward_name", fileNameArray);
				jsonObject.put("all", "all");
			}
			return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("File Could not be found", HttpStatus.OK);
		}
	}
	
	 
	@Scheduled(cron = "0 30 6 * * *")// Run on every day 6:30 AM // Run on every saturday at 11 PM // 0 0 23 * * *
	@RequestMapping(value = "/insert_re_integration_data", method = RequestMethod.GET, produces = "application/json")
	public String insert_re_integration_data() { 
		
		System.out.println("Scheduler start .. ");
		String reIntegrationUrl = environment.getProperty("REIntegration.api.url");
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity <String> entity = new HttpEntity<String>(headers);

		ResponseEntity<String> response = restTemplate.exchange(reIntegrationUrl, HttpMethod.GET,entity, String.class);
		//JSONObject response = new JSONObject();
		//JSONObject requestObj = new JSONObject("{ \"data\": [ { \"referenceNumber\": \"145066\", \"complaintNumber\": \"A/225/10-01-2024/9\", \"complaintLatLng\": \"nnanaa\", \"complaintImageUrl\": \"http://203.129.224.98:8085/document/BFTS/Complaint/72141/IR/81161/Stage/9/Image/72141_81161_9_1.jpg\", \"complaintCreated\": \"Yes\", \"noticeIssued\": \"No\", \"speakingOrderIssued\": \"No\", \"demolitionDone\": \"No\", \"noticeClosed\": \"No\" }, { \"referenceNumber\": \"145300\", \"complaintNumber\": \"A/225/10-01-2024/14\", \"complaintLatLng\": \"123456\", \"complaintImageUrl\": \"http://203.129.224.98:8085/document/BFTS/Complaint/72141/IR/81161/Stage/9/Image/72141_81161_9_1.jpg\", \"complaintCreated\": \"Yes\", \"noticeIssued\": \"No\", \"speakingOrderIssued\": \"No\", \"demolitionDone\": \"No\", \"noticeClosed\": \"No\" }, { \"referenceNumber\": \"145066\", \"complaintNumber\": \"A/225/10-01-2024/13\", \"complaintLatLng\": \"1234567\", \"complaintImageUrl\": \"http://203.129.224.98:8085/document/BFTS/Complaint/72141/IR/81161/Stage/9/Image/72141_81161_9_1.jpg\", \"complaintCreated\": \"Yes\", \"noticeIssued\": \"No\", \"speakingOrderIssued\": \"No\", \"demolitionDone\": \"No\", \"noticeClosed\": \"No\" }, { \"referenceNumber\": \"145300\", \"complaintNumber\": \"A/225/10-01-2024/12\", \"complaintLatLng\": \"1234556778\", \"complaintImageUrl\": \"http://203.129.224.98:8085/document/BFTS/Complaint/72141/IR/81161/Stage/9/Image/72141_81161_9_1.jpg\", \"complaintCreated\": \"Yes\", \"noticeIssued\": \"No\", \"speakingOrderIssued\": \"No\", \"demolitionDone\": \"No\", \"noticeClosed\": \"No\" }, { \"referenceNumber\": \"145302\", \"complaintNumber\": \"A/225/10-01-2024/11\", \"complaintLatLng\": \"123455\", \"complaintImageUrl\": \"http://203.129.224.98:8085/document/BFTS/Complaint/72141/IR/81161/Stage/9/Image/72141_81161_9_1.jpg\", \"complaintCreated\": \"Yes\", \"noticeIssued\": \"No\", \"speakingOrderIssued\": \"No\", \"demolitionDone\": \"No\", \"noticeClosed\": \"No\" }, { \"referenceNumber\": \"145066\", \"complaintNumber\": \"A/225/10-01-2024/10\", \"complaintLatLng\": \"1233445\", \"complaintImageUrl\": \"http://203.129.224.98:8085/document/BFTS/Complaint/72141/IR/81161/Stage/9/Image/72141_81161_9_1.jpg\", \"complaintCreated\": \"Yes\", \"noticeIssued\": \"No\", \"speakingOrderIssued\": \"No\", \"demolitionDone\": \"No\", \"noticeClosed\": \"No\" }, { \"referenceNumber\": \"145300\", \"complaintNumber\": \"A/225/10-01-2024/15\", \"complaintLatLng\": \"bbvbgg\", \"complaintImageUrl\": \"http://203.129.224.98:8085/document/BFTS/Complaint/72141/IR/81161/Stage/9/Image/72141_81161_9_1.jpg\", \"complaintCreated\": \"Yes\", \"noticeIssued\": \"No\", \"speakingOrderIssued\": \"No\", \"demolitionDone\": \"No\", \"noticeClosed\": \"No\" } ] }");
		//response.put("data",requestObj.toString());
		
		if(response.getBody() != null) {
			System.out.println("Api Response is " + response.getBody());
			if(response.getBody().equals("Record Not Found")) {
				return response.getBody().toString();
			}else {
				//JSONObject responseObj = new JSONObject(response.getBody().toString());
				JSONObject mainObj = new JSONObject();
				mainObj.put("data", response.getBody().toString());
				System.out.println("Response data is: " + mainObj.toString());
				String result = propertySurveyService.insert_re_integration_data(mainObj.toString());
				return result;
			}
		}
		
		//return response.getBody().toString();
		//System.out.println("json data " + requestObj.toString());
		//String result = propertySurveyService.insert_re_integration_data(response.toString());
		//return result;
		
		return null;
	}
	
	@ApiIgnore
	@RequestMapping(value = "/get_re_integration_data", method = RequestMethod.POST)
	public ResponseEntity<?> getREIntegrationData(@RequestBody String reqJson,HttpServletRequest request) {
		try {
			String result = propertySurveyService.getREIntegrationData(reqJson);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}
	
	@ApiIgnore
	@RequestMapping(value = "/get_state_name", method = RequestMethod.GET)
	public ResponseEntity<?> getAllStateName(HttpServletRequest request) {
		try {
			String result = propertySurveyService.getAllStateName();
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}
	
	@ApiIgnore
	@RequestMapping(value = "/get_ward_id/{state_name}", method = RequestMethod.POST)
	public ResponseEntity<?> getWardIdByStateName(@PathVariable("state_name") String stateName ,HttpServletRequest request) {
		try {
			String result = propertySurveyService.getwardIdByStateName(stateName);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}
	@ApiIgnore
	@RequestMapping(value = "/grid_data_by_ward/{wardId}", method = RequestMethod.POST)
	public ResponseEntity<?> gridDataByWardId(@PathVariable("wardId") String wardId ,HttpServletRequest request) {
		try {
			String result = propertySurveyService.gridDataByWardId(wardId);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}
	@ApiIgnore
	@RequestMapping(value = "/get_url", method = RequestMethod.POST)
	public ResponseEntity<?> getUrl(@RequestBody String reqJson,HttpServletRequest request) {
		try {
			String result = propertySurveyService.getUrl(reqJson);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(CustomMessages.getMessage(CustomMessages.RESPONSE_MESSAGE_500));
		}
	}
	
	
}
