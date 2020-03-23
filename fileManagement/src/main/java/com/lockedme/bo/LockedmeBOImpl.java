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
	return dAOItem.addFile(filename);
}

@Override
public FileObj deleteFile(String fileName) throws BusinessException {
	LockedmeDAOImpl dAOItem = new LockedmeDAOImpl();
	return dAOItem.deleteFile(fileName);
}

@Override
public List<FileObj> searchFile(String fileName) throws BusinessException {
	LockedmeDAOImpl dAOItem = new LockedmeDAOImpl();
	return dAOItem.searchFile(fileName);
}}
