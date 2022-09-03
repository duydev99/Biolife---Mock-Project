package com.jsfw.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class UploadImage {
	static Path currentDir = Paths.get("src/main/resources/static/images");
	public static Path CURRENT_FOLDER = currentDir.toAbsolutePath();
	public static void uploadImageUtil(MultipartFile[] files,String id) {
		// Thư mục gốc upload file.
				File uploadRootDir = new File(CURRENT_FOLDER.toString());
				System.out.println(uploadRootDir);
				// Tạo thư mục gốc upload nếu nó không tồn tại.
				if (!uploadRootDir.exists()) {
					uploadRootDir.mkdirs();
				}
				for(MultipartFile img:files) {
					// Tên file gốc tại Client.
					String name = img.getOriginalFilename();
					
					if (name != null && name.length() > 0) {
						try {
							// Tạo file tại Server.
							File serverFile = new File(uploadRootDir.getAbsolutePath() + "/"+id+"_"+ name);
							System.out.println(serverFile);
							BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
							stream.write(img.getBytes());
							stream.close();
						} catch (Exception e) {
							System.out.println("Error Write file: " + name);
						}
					}
				}
	}}
