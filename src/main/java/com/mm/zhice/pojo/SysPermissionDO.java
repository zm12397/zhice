package com.mm.zhice.pojo;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 权限表
 * @author zm
 *
 */
@Entity
@Table(name = "sys_permission")
@DynamicUpdate
@DynamicInsert
public class SysPermissionDO  implements Serializable{
	// 无关主键ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 10)
	private Long id;

	// 权限名称
	@Column(length = 30, nullable = false)
	private String name;
	// 资源类型
	@Column(length = 10)
	private String type;
	// 资源路径
	@Column(length = 50, nullable = false)
	private String url;
	// 权限字符串
	@Column(length = 50)
	private String permission;

	// 状态：0无效 1有效
	@Column(length = 1)
	private Short state;

	// 角色权限关系映射
	@OneToMany(mappedBy = "sysPermission")
	private List<SysRolePermissionDO> sysRolePermissions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public List<SysRolePermissionDO> getSysRolePermissions() {
		return sysRolePermissions;
	}

	public void setSysRolePermissions(List<SysRolePermissionDO> sysRolePermissions) {
		this.sysRolePermissions = sysRolePermissions;
	}

	public SysPermissionDO(String name, String type, String url, String permission, Short state, List<SysRolePermissionDO> sysRolePermissions) {
		this.name = name;
		this.type = type;
		this.url = url;
		this.permission = permission;
		this.state = state;
		this.sysRolePermissions = sysRolePermissions;
	}

	public SysPermissionDO() {
	}

	@Override
	public String toString() {
		return "SysPermissionDO{" +
				"id=" + id +
				", name='" + name + '\'' +
				", type='" + type + '\'' +
				", url='" + url + '\'' +
				", permission='" + permission + '\'' +
				", state=" + state +
				", sysRolePermissions=" + sysRolePermissions +
				'}';
	}
}
