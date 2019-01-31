package com.mm.zhice.service;

import java.util.Map;

public interface ShiroService {

	Map<String, String> getInitUrlPermission();
	
	void updatePermission();
}
