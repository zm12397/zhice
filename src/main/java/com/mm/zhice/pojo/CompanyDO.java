package com.mm.zhice.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "company")
@DynamicUpdate
@DynamicInsert
public class CompanyDO {
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

    @Column(length = 50)
    private String name;

    @Column(length = 100)
    private String number;

    // 公司状态： 默认0创建未认证 1有效
    @Column(length = 1)
    private Short state;

    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<SysUserDO> users;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public List<SysUserDO> getUsers() {
        return users;
    }

    public void setUsers(List<SysUserDO> users) {
        this.users = users;
    }

    public CompanyDO(Date gmtCreate, Date gmtModified, String name, String number, Short state, List<SysUserDO> users) {
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.name = name;
        this.number = number;
        this.state = state;
        this.users = users;
    }

    public CompanyDO() {
    }

    @Override
    public String toString() {
        return "CompanyDO{" +
                "id=" + id +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", state=" + state +
                ", users=" + users +
                '}';
    }
}
