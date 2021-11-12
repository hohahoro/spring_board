package org.bbs.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.bbs.model.AttachFileDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnailator;

@Controller
public class UploadController {
	
	@GetMapping("/uploadForm")
	public String uploadForm() {
		System.out.println("uploadForm 진입 성공");
		return "uploadForm";
	}
	
	@PostMapping("/uploadFormAction")
	public void uploadFormAction(MultipartFile[] uploadFile, Model model) {
		
		String uploadFolder = "C:\\upload";
		
		File uploadPath = new File(uploadFolder, getFolder());
		
		for(MultipartFile multipartFile : uploadFile) {
			System.out.println("-------------------------");
			System.out.println("업로드된 파일 이름 : " + multipartFile.getOriginalFilename());
			System.out.println("업로드된 파일 크기 : " + multipartFile.getSize());
			
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				System.out.println("파일 저장 실패");
			}
		}
		
		
	}
	
	@GetMapping("/uploadAjax")
	public String uploadAjax() {
		System.out.println("uploadAjax 진입 성공");
		return "uploadAjax";
	}
	
	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
//  ajax 사용시 얼러트가 안뜨면 사용
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxAction(MultipartFile[] uploadFile) {

		List<AttachFileDTO> list = new ArrayList<AttachFileDTO>();
		
		String uploadFolder = "C:\\upload";
		
		String uploadFolderPath = getFolder();
		
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		
		if(uploadPath.exists() == false) {
//			폴더를 만들어라
			uploadPath.mkdirs();
		}
		
		
		for(MultipartFile multipartFile : uploadFile) {
//			System.out.println("-------------------------");
//			System.out.println("업로드된 파일 이름 : " + multipartFile.getOriginalFilename());
//			System.out.println("업로드된 파일 크기 : " + multipartFile.getSize());
			
			AttachFileDTO attachDTO = new AttachFileDTO();
			
//			들어오는 파일 이름 지정
			String uploadFileName = multipartFile.getOriginalFilename();
			
			attachDTO.setFileName(uploadFileName);
			
//			같은 파일 이름이 잇더라면 +1
			uploadFileName = uploadFileName.substring(
					uploadFileName.lastIndexOf("\\")+1
					);
//			중복방지		
			UUID uuid = UUID.randomUUID();
			
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			
			File saveFile = new File(uploadPath, uploadFileName);
		
			try {
				multipartFile.transferTo(saveFile);
				
				attachDTO.setUuid(uuid.toString());
				attachDTO.setUploadPath(uploadFolderPath);
				
//				썸네일 추가
				if(checkImageType(saveFile)) {
					
					attachDTO.setImage(true);
//					saveFile이 image라면
					FileOutputStream thubnail = new FileOutputStream(
							new File(uploadPath, "t_" + uploadFileName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thubnail, 100, 100);
					thubnail.close();
				}
				
				list.add(attachDTO);
				
			} catch (Exception e) {
				System.out.println("파일 저장 실패");
			}
		}
	
		return new ResponseEntity<>(list, HttpStatus.OK);
		
	}	
	
	@GetMapping("/display")
	@ResponseBody
//	fileName으로 넘겨줫음
	public ResponseEntity<byte[]> getFile(String fileName){
		
		String uploadFolder = "C:\\upload\\";
		
		File file = new File(uploadFolder + fileName);
		
		ResponseEntity<byte[]> result = null;
		
		try {

			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), 
	                  header, HttpStatus.OK);
			
		} catch (Exception e) {

		}
		
		return result;
		
	}
	
	
	private String getFolder() {
		
//		년, 월 , 일로 저장 mm 소문자 주의 << 대문자 안하면 분으로 나오는거 같음
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
//		-를 기준으로 파일별로 분류 (년도, 월 , 일)
		String str = sdf.format(date);
		return str.replace("-", File.separator);
		
		
	}
	
//	썸네일 만들기
	private boolean checkImageType(File file) {
		
		try {
			
//			image file이면 참, 아니면 거짓
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
}
