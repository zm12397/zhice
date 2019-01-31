package com.mm.zhice.pojo;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 角色权限多对多关联表
 * @author zm
 *
 */
@Entity
@Table(name = "sys_role_permission", uniqueConstraints = { @UniqueConstraint(columnNames = {"role_id","permission_id" }) })
@DynamicUpdate
@DynamicInsert
public class SysRolePermissionDO  implements Serializable{
	// 无关主键ID
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 10)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private SysRoleDO sysRole;
	@ManyToOne
	@JoinColumn(name = "permission_id")
	private SysPermissionDO sysPermission;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SysRoleDO getSysRole() {
		return sysRole;
	}

	public void setSysRole(SysRoleDO sysRole) {
		this.sysRole = sysRole;
	}

	public SysPermissionDO getSysPermission() {
		return sysPermission;
	}

	public void setSysPermission(SysPermissionDO sysPermission) {
		this.sysPermission = sysPermission;
	}

	public SysRolePermissionDO(SysRoleDO sysRole, SysPermissionDO sysPermission) {
		this.sysRole = sysRole;
		this.sysPermission = sysPermission;
	}

	public SysRolePermissionDO() {
	}

	@Override
	public String toString() {
		return "SysRolePermissionDO{" +
				"id=" + id +
				", sysRole=" + sysRole.getId() +
				", sysPermission=" + sysPermission.getId() +
				'}';
	}
}
