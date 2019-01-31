package com.mm.zhice.service.impl;

import com.mm.zhice.dao.UrlPermissionInitDao;
import com.mm.zhice.pojo.UrlPermissionInitDO;
import com.mm.zhice.service.ShiroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ShiroServiceImpl implements ShiroService {
	private Logger log = LoggerFactory.getLogger(ShiroService.class);
	/*@Autowired
	private ShiroFilterFactoryBean shiroFilterFactoryBean;*/
	@Autowired
	private UrlPermissionInitDao urlPermissionInitDao;

	@Override
	public Map<String, String> getInitUrlPermission() {
		// TODO Auto-generated method stub
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		List<UrlPermissionInitDO> urlPermissionInits = urlPermissionInitDao.findByOrderBySeq();
		for (UrlPermissionInitDO urlPermissionInit : urlPermissionInits) {
			String url = urlPermissionInit.getUrl();
			String permission = urlPermissionInit.getPermissionName();
			filterChainDefinitionMap.put(url, permission);
			log.info(url + "：" + permission);
		}
		return filterChainDefinitionMap;
	}
	
	/**
	 * 重新加载权限
	 */
	@Override
	public void updatePermission() {
		/*synchronized (shiroFilterFactoryBean) {
			AbstractShiroFilter shiroFilter = null;
			try {
				shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean
						.getObject();
			} catch (Exception e) {
				throw new RuntimeException(
						"get ShiroFilter from shiroFilterFactoryBean error!");
			}
			PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter
					.getFilterChainResolver();
			DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver
					.getFilterChainManager();
			// 清空老的权限控制
			manager.getFilterChains().clear();
			shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
			shiroFilterFactoryBean
					.setFilterChainDefinitionMap(getInitUrlPermission());
			// 重新构建生成
			Map<String, String> chains = shiroFilterFactoryBean
					.getFilterChainDefinitionMap();
			for (Map.Entry<String, String> entry : chains.entrySet()) {
				String url = entry.getKey();
				String chainDefinition = entry.getValue().trim()
						.replace(" ", "");
				manager.createChain(url, chainDefinition);
			}
			System.out.println("更新权限成功！！");
		}*/
	}
}
