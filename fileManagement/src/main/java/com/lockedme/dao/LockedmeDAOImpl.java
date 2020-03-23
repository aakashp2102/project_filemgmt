package com.lockedme.dao;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.lockedme.exception.BusinessException;
import com.lockedme.model.FileObj;

public class LockedmeDAOImpl implements LockedmeDAO {
	public String initialiseFiles() throws BusinessException {

		System.out.println("Fetching Root Directory . . .");
		String userDirPath = System.getProperty("user.dir") + "\\bin";
		File maindir = new File(userDirPath);
		maindir.mkdir();

		return userDirPath;

	}

	@Override
	public List<FileObj> listFiles() throws BusinessException {
		String userDirPath = initialiseFiles();
		File userDir = new File(userDirPath);
		File arr[] = userDir.listFiles();
		List<FileObj> fileList = new LinkedList<>();
		
		if(arr.length>0) {
		for (File e : arr) {
			String fileName = e.getName();

			String filePath = e.getPath();
			String fileType = "Folder";

			long fileSize;
			if (e.getName().contains(".")) {
				fileType = fileName.substring(fileName.lastIndexOf("."));
				fileName = fileName.substring(0, fileName.lastIndexOf("."));
				fileSize = e.length();

			} else {
				long size = FileUtils.sizeOfDirectory(e);
				fileSize = size;

			}
			FileObj file = new FileObj(fileName, filePath, fileType, fileSize);
			fileList.add(file);
		}
		}
		else {
			throw new BusinessException("Root Directory is empty. Root Directory: "+userDirPath);
		}
	
		Collections.sort(fileList);

		return fileList;
	}

	@Override
	public FileObj addFile(String fileName) throws BusinessException, IOException {

		File file = new File(System.getProperty("user.dir") + "\\bin" + "\\" + fileName);
		boolean result;
			if (fileName.contains(".")) {
				result=file.createNewFile();
			} else {
				result=file.mkdir();
			}
		if (result){
			
				String fname = file.getName();

		String filePath = file.getPath();
		String fileType = "Folder";

		long fileSize;
		if (file.getName().contains(".")) {
			fileType = fname.substring(fname.lastIndexOf("."));
			fileName = fname.substring(0, fname.lastIndexOf("."));
			fileSize = file.length();

		} else {
			long size = FileUtils.sizeOfDirectory(file);
			fileSize = size;

		}

		FileObj addedFile = new FileObj(fname, filePath, fileType, fileSize);
		return addedFile;}
		else
		{
			throw new BusinessException("The file/folder could not be created. Please check if file/folder with name "+fileName+" alread exist");
		}

	}

	@Override
	public FileObj deleteFile(String fileName) throws BusinessException {
		// TODO Auto-generated method stub
		File file = new File(System.getProperty("user.dir") + "\\bin" + "\\" + fileName);
		String fname = file.getName();

		String filePath = file.getPath();
		String fileType = "Folder";
		boolean result;

		long fileSize;
		if (file.getName().contains(".")) {
			fileType = fname.substring(fname.lastIndexOf("."));
			fileName = fname.substring(0, fname.lastIndexOf("."));
			fileSize = file.length();

		} else {
			long size = FileUtils.sizeOfDirectory(file);
			fileSize = size;

		}

		result=file.delete();
		if(result) {
		FileObj deletedFile = new FileObj(fname, filePath, fileType, fileSize);
		return deletedFile;}
		else {
			throw new BusinessException("Could not Delete "+fileName+". Note:Folders can be deleted only if they are empty.");
		}

	}

	@Override
	public List<FileObj> searchFile(String fileName) throws BusinessException {
		// TODO Auto-generated method stub
		List<FileObj> fileList = new LinkedList<>();
		
		for (FileObj e : listFiles()) {
			
			if (e.getFileName().equals(fileName)) {
			fileList.add(e);}
		}
		if(fileList.isEmpty()) {
			throw new BusinessException("No file with "+fileName+" found.");
		}
		else {
		return fileList;}
	}

}
