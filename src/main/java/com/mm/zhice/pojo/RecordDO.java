package com.mm.zhice.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "records")
@DynamicUpdate
@DynamicInsert
public class RecordDO {
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

    @Column(name = "final_life",precision = 4, scale = 2)
    private Double finalLife;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "committer_id")
    private SysUserDO committer;

    @OneToOne
    @JoinColumn(name = "data_file_id")
    private DataFileDO dataFile;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "knife_id")
    private KnifeDO knife;

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

    public Double getFinalLife() {
        return finalLife;
    }

    public void setFinalLife(Double finalLife) {
        this.finalLife = finalLife;
    }

    public SysUserDO getCommitter() {
        return committer;
    }

    public void setCommitter(SysUserDO committer) {
        this.committer = committer;
    }

    public DataFileDO getDataFile() {
        return dataFile;
    }

    public void setDataFile(DataFileDO dataFile) {
        this.dataFile = dataFile;
    }

    public KnifeDO getKnife() {
        return knife;
    }

    public void setKnife(KnifeDO knife) {
        this.knife = knife;
    }

    public RecordDO(Date gmtCreate, Date gmtModified, Double finalLife, SysUserDO committer, DataFileDO dataFile, KnifeDO knife) {
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.finalLife = finalLife;
        this.committer = committer;
        this.dataFile = dataFile;
        this.knife = knife;
    }

    public RecordDO() {
    }

    @Override
    public String toString() {
        return "RecordDO{" +
                "id=" + id +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", finalLife=" + finalLife +
                ", committer=" + committer.getId() +
                ", knife=" + knife.getId() +
                ", dataFile=" + dataFile.getId() +
                '}';
    }
}
