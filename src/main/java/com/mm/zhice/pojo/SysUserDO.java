package com.mm.zhice.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户表
 * 
 * @author zm
 *
 */
@Entity
@Table(name = "sys_user", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
@DynamicUpdate
@DynamicInsert
public class SysUserDO implements Serializable {
	// 无关主键ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 10)
	private Long id;

	// 创建时间戳
	@Column(name = "gmt_create", insertable = true, updatable = false, columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date gmtCreate;
	// 修改时间戳
	@Column(name = "gmt_modified", insertable = false, updatable = true, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date gmtModified;

	// 用户名
	@Column(length = 50, nullable = false)
	private String username;
	// 密码
	@Column(length = 50, nullable = false)
	private String password;

	// 用户状态： 默认0创建未认证 1有效
	@Column(length = 1)
	private Short state;

	@Column(length = 50)
	private String number;

	// 证件类型，1为身份证
	@Column(name = "number_type")
	private Long numberType;

	// 姓名
	@Column(length = 50)
	private String name;

	@Column(length = 100)
	private String info;
	@Column(length = 100)
	private String remark;


	// 密码盐
	@Column(length = 50)
	private String salt;

	// 性别： 0男 1女
	@Column(length = 1)
	private Short sex;
	// 电话
	@Column(length = 50)
	private String tel;
	@Column(length = 50)
	private String email;
	@Column(length = 50)
	private String address;

	// 生日 16位unix时间戳
	private Date birthday;
	@Column(length = 10)
	private String validation;
	private Long vtime;
//	private ImageFileDO image;
//	private CompanyDO company;

	// 用户角色关系映射
	@JsonIgnore
	@OneToMany(mappedBy = "sysUser",fetch = FetchType.EAGER)
	private List<SysUserRoleDO> sysUserRoles;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "company_id")
	private CompanyDO company;

	@JsonIgnore
	@OneToMany(mappedBy = "committer")
	private List<KnifeDO> knifes;

	// 用户头像映射
	@OneToOne
	@JoinColumn(name = "image_id")
	private ImgFileDO image;

	@JsonIgnore
	@OneToMany(mappedBy = "committer")
	private List<RecordDO> records;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SysUserDO other = (SysUserDO) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	public SysUserDO(Date gmtCreate, Date gmtModified, String username, String password, Short state, String number, Long numberType, String name, String info, String remark, String salt, Short sex, String tel, String email, String address, Date birthday, String validation, Long vtime, List<SysUserRoleDO> sysUserRoles, CompanyDO company, List<KnifeDO> knifes, ImgFileDO image, List<RecordDO> records) {
		this.gmtCreate = gmtCreate;
		this.gmtModified = gmtModified;
		this.username = username;
		this.password = password;
		this.state = state;
		this.number = number;
		this.numberType = numberType;
		this.name = name;
		this.info = info;
		this.remark = remark;
		this.salt = salt;
		this.sex = sex;
		this.tel = tel;
		this.email = email;
		this.address = address;
		this.birthday = birthday;
		this.validation = validation;
		this.vtime = vtime;
		this.sysUserRoles = sysUserRoles;
		this.company = company;
		this.knifes = knifes;
		this.image = image;
		this.records = records;
	}

	public SysUserDO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Long getNumberType() {
		return numberType;
	}

	public void setNumberType(Long numberType) {
		this.numberType = numberType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Short getSex() {
		return sex;
	}

	public void setSex(Short sex) {
		this.sex = sex;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getValidation() {
		return validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}

	public Long getVtime() {
		return vtime;
	}

	public void setVtime(Long vtime) {
		this.vtime = vtime;
	}

	public List<SysUserRoleDO> getSysUserRoles() {
		return sysUserRoles;
	}

	public void setSysUserRoles(List<SysUserRoleDO> sysUserRoles) {
		this.sysUserRoles = sysUserRoles;
	}

	public CompanyDO getCompany() {
		return company;
	}

	public void setCompany(CompanyDO company) {
		this.company = company;
	}

	public List<KnifeDO> getKnifes() {
		return knifes;
	}

	public void setKnifes(List<KnifeDO> knifes) {
		this.knifes = knifes;
	}

	public ImgFileDO getImage() {
		return image;
	}

	public void setImage(ImgFileDO image) {
		this.image = image;
	}

	public List<RecordDO> getRecords() {
		return records;
	}

	public void setRecords(List<RecordDO> records) {
		this.records = records;
	}

	@Override
	public String toString() {
		return "SysUserDO{" +
				"id=" + id +
				", gmtCreate=" + gmtCreate +
				", gmtModified=" + gmtModified +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", state=" + state +
				", number='" + number + '\'' +
				", numberType=" + numberType +
				", name='" + name + '\'' +
				", info='" + info + '\'' +
				", remark='" + remark + '\'' +
				", salt='" + salt + '\'' +
				", sex=" + sex +
				", tel='" + tel + '\'' +
				", email='" + email + '\'' +
				", address='" + address + '\'' +
				", birthday=" + birthday +
				", validation='" + validation + '\'' +
				", vtime=" + vtime +
				", sysUserRoles=" + sysUserRoles +
				", company=" + company.getId() +
				", knifes=" + knifes +
				", image=" + image.getId() +
				", records=" + records +
				'}';
	}
}
