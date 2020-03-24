package com.lockedme.bo;

import java.io.IOException;
import java.util.List;

import com.lockedme.dao.LockedmeDAOImpl;
import com.lockedme.exception.BusinessException;
import com.lockedme.model.FileObj;

public class LockedmeBOImpl implements LockedmeBO
{

	@Override
	public List<FileObj> listFiles() throws BusinessException {
		// TODO Auto-generated method stub
		LockedmeDAOImpl dAOItem = new LockedmeDAOImpl();
		return dAOItem.listFiles();
	}

	@Override
	public FileObj addFile(String filename) throws BusinessException, IOException {
		LockedmeDAOImpl dAOItem = new LockedmeDAOImpl();
		
		String filePrefix;
		String fileSuffix;
		if (filename.contains(".")){
			filePrefix=filename.substring(0, filename.lastIndexOf("."));
			fileSuffix=filename.substring(filename.lastIndexOf(".")).toLowerCase();}
		
		else {
			filePrefix=filename;
			fileSuffix="";
		}
		System.out.println(filePrefix);
		System.out.println(fileSuffix);
		if((!filePrefix.contains("."))&& filePrefix.matches("^[A-Za-z0-9\\s]+$")) {
			if(fileSuffix.equals("") || fileSuffix.equals(".txt") || fileSuffix.equals(".docx")) {

				FileObj file=dAOItem.addFile(filename);
				return file;

			}
			else {
				throw new BusinessException("Incorrect file type, suffix for file type can only be .docx, .txt or no suffix for folder ");
			}
		}
		else {
			throw new BusinessException("File name should only contain alpha numeric character and white spaces");
		}
	}



@Override
public FileObj deleteFile(String fileName) throws BusinessException {
	LockedmeDAOImpl dAOItem = new LockedmeDAOImpl();
	
	String filePrefix;
	String fileSuffix;
	if (fileName.contains(".")){
		filePrefix=fileName.substring(0, fileName.lastIndexOf("."));
		fileSuffix=fileName.substring(fileName.lastIndexOf(".")).toLowerCase();}
	else {
		filePrefix=fileName;
		fileSuffix="";
	}
	if((!filePrefix.contains("."))&& filePrefix.matches("^[A-Za-z0-9\\s]+$")) {
		if(fileSuffix.equals("") || fileSuffix.equals(".txt") || fileSuffix.equals(".docx")) {

			FileObj file=dAOItem.deleteFile(fileName);
			return file;

		}
		else {
			throw new BusinessException("Incorrect file type, suffix for file type can only be .docx, .txt or no suffix for folder ");
		}
	}
	else {
		throw new BusinessException("File name should only contain alpha numeric character and white spaces");
	}
}

@Override
public List<FileObj> searchFile(String fileName) throws BusinessException {
	LockedmeDAOImpl dAOItem = new LockedmeDAOImpl();
	
	
	if((!fileName.contains("."))&& fileName.matches("^[A-Za-z0-9\\s]+$")) {
		return dAOItem.searchFile(fileName);
	}
	else {
		throw new BusinessException("File name should only contain alpha numeric character and white spaces. please dont provide file extention to search files");
	}
}
}
