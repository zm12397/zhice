package com.mm.zhice.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户角色多对多关联表
 * @author zm
 *
 */
@Entity
@Table(name = "sys_user_role", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id", "role_id" }) })
@DynamicUpdate
@DynamicInsert
public class SysUserRoleDO  implements Serializable{
	// 无关主键ID
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 10)
	private Long id;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "user_id")
	private SysUserDO sysUser;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "role_id")
	private SysRoleDO sysRole;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SysUserDO getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUserDO sysUser) {
		this.sysUser = sysUser;
	}

	public SysRoleDO getSysRole() {
		return sysRole;
	}

	public void setSysRole(SysRoleDO sysRole) {
		this.sysRole = sysRole;
	}

	public SysUserRoleDO(SysUserDO sysUser, SysRoleDO sysRole) {
		this.sysUser = sysUser;
		this.sysRole = sysRole;
	}

	public SysUserRoleDO() {
	}

	@Override
	public String toString() {
		return "SysUserRoleDO{" +
				"id=" + id +
				", sysUser=" + sysUser.getId() +
				", sysRole=" + sysRole.getId() +
				'}';
	}
}
