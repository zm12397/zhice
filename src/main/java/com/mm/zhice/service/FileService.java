package com.mm.zhice.service;

import com.mm.zhice.pojo.DataFileDO;

import java.io.File;

public interface FileService {
	boolean validate(String filename);
	File saveFile(String filename, String path);
	void addDataFile(DataFileDO dataFile);

	DataFileDO getDataFile(Long fileId);

	void update(DataFileDO dataFile);
}
