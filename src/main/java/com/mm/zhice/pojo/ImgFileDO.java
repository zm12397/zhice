package com.mm.zhice.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "img_file")
@DynamicUpdate
@DynamicInsert
public class ImgFileDO {
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

    @Column(length = 100)
    private String path;

    @JsonIgnore
    @OneToOne(mappedBy = "image")
    private SysUserDO user;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public SysUserDO getUser() {
        return user;
    }

    public void setUser(SysUserDO user) {
        this.user = user;
    }

    public ImgFileDO(Date gmtCreate, Date gmtModified, String path, SysUserDO user) {
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.path = path;
        this.user = user;
    }

    public ImgFileDO() {
    }

    @Override
    public String toString() {
        return "ImgFileDO{" +
                "id=" + id +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", path='" + path + '\'' +
                ", user=" + user.getId() +
                '}';
    }
}
