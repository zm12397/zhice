package com.mm.zhice.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "knife")
@DynamicUpdate
@DynamicInsert
public class KnifeDO {
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

    // 刀具状态： 默认0创建未认证 1有效
    @Column(length = 1)
    private Short state;

    @Column(length = 50)
    private String name;

    @Column(length = 100)
    private String number;

    @Column(precision = 4, scale = 2)
    private Double life;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "committer_id")
    private SysUserDO committer;

    @JsonIgnore
    @OneToMany(mappedBy = "knife")
    private List<DataFileDO> dataFiles;

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

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
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

    public Double getLife() {
        return life;
    }

    public void setLife(Double life) {
        this.life = life;
    }

    public SysUserDO getCommitter() {
        return committer;
    }

    public void setCommitter(SysUserDO committer) {
        this.committer = committer;
    }

    public List<DataFileDO> getDataFiles() {
        return dataFiles;
    }

    public void setDataFiles(List<DataFileDO> dataFiles) {
        this.dataFiles = dataFiles;
    }

    public KnifeDO(Date gmtCreate, Date gmtModified, Short state, String name, String number, Double life, SysUserDO committer, List<DataFileDO> dataFiles) {
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.state = state;
        this.name = name;
        this.number = number;
        this.life = life;
        this.committer = committer;
        this.dataFiles = dataFiles;
    }

    public KnifeDO() {
    }

    @Override
    public String toString() {
        return "KnifeDO{" +
                "id=" + id +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", state=" + state +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", life=" + life +
                ", committer=" + committer.getId() +
                ", dataFiles=" + dataFiles +
                '}';
    }
}
