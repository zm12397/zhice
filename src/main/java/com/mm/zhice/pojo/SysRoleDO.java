package com.mm.zhice.pojo;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 角色表
 * 
 * @author zm
 *
 */
@Entity
@Table(name = "sys_role")
@DynamicUpdate
@DynamicInsert
public class SysRoleDO  implements Serializable{
	// 无关主键ID
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 10)
	private Long id;
	// 角色名称
	@Column(length = 30, nullable = false)
	private String role;
	// 角色描述
	@Column(length = 50)
	private String description;

	// 状态：0无效 1有效
	@Column(length = 1)
	private Short state;

	// 用户角色关系映射
	@OneToMany(mappedBy = "sysRole")
	private List<SysUserRoleDO> sysUserRoleDOes;

	// 角色权限关系映射
	@OneToMany(mappedBy = "sysRole",fetch = FetchType.EAGER)
	private List<SysRolePermissionDO> sysRolePermissions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public List<SysUserRoleDO> getSysUserRoleDOes() {
		return sysUserRoleDOes;
	}

	public void setSysUserRoleDOes(List<SysUserRoleDO> sysUserRoleDOes) {
		this.sysUserRoleDOes = sysUserRoleDOes;
	}

	public List<SysRolePermissionDO> getSysRolePermissions() {
		return sysRolePermissions;
	}

	public void setSysRolePermissions(List<SysRolePermissionDO> sysRolePermissions) {
		this.sysRolePermissions = sysRolePermissions;
	}

	public SysRoleDO(String role, String description, Short state, List<SysUserRoleDO> sysUserRoleDOes, List<SysRolePermissionDO> sysRolePermissions) {
		this.role = role;
		this.description = description;
		this.state = state;
		this.sysUserRoleDOes = sysUserRoleDOes;
		this.sysRolePermissions = sysRolePermissions;
	}

	public SysRoleDO() {
	}

	@Override
	public String toString() {
		return "SysRoleDO{" +
				"id=" + id +
				", role='" + role + '\'' +
				", description='" + description + '\'' +
				", state=" + state +
				", sysUserRoleDOes=" + sysUserRoleDOes +
				", sysRolePermissions=" + sysRolePermissions +
				'}';
	}
}
