package com.mm.zhice.pojo;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 权限路径初始化表
 * @author zm
 *
 */
@Entity
@Table(name = "url_permission_init")
@DynamicUpdate
@DynamicInsert
public class UrlPermissionInitDO  implements Serializable{
	// 无关主键ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 10)
	private Integer id;
	// 初始化权限的路径
	@Column(length = 50, nullable = false)
	private String url;
	// 初始化权限的名称
	@Column(name ="permission_name",length = 30, nullable = false)
	private String permissionName;
	// 初始化权限的顺序
	@Column(length = 4, nullable = false)
	private Integer seq;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public UrlPermissionInitDO(String url, String permissionName, Integer seq) {
		this.url = url;
		this.permissionName = permissionName;
		this.seq = seq;
	}

	public UrlPermissionInitDO() {
	}

	@Override
	public String toString() {
		return "UrlPermissionInitDO{" +
				"id=" + id +
				", url='" + url + '\'' +
				", permissionName='" + permissionName + '\'' +
				", seq=" + seq +
				'}';
	}
}
