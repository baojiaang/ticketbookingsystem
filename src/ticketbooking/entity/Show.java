/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketbooking.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author baojiaang
 */
@Entity
@Table(name = "SHOW")
@NamedQueries({
    @NamedQuery(name = "Show.findAll", query = "SELECT s FROM Show s")})
public class Show implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "LOCATION")
    private String location;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PIC_PATH")
    private String picPath;
    @Column(name = "SHOW_TIME")
    @Temporal(TemporalType.DATE)
    private Date showTime;
    @Column(name = "DETAIL")
    private String detail;
    @Column(name = "ROW")
    private Integer row;
    @Column(name = "COL")
    private Integer col;

    public Show(Integer id, String name, String location, String description, String picPath, Date showTime, String detail, Integer row, Integer col) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.picPath = picPath;
        this.showTime = showTime;
        this.detail = detail;
        this.row = row;
        this.col = col;
    }

    public Show(String name, String location, String description, String picPath, Date showTime, String detail, Integer row, Integer col) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.picPath = picPath;
        this.showTime = showTime;
        this.detail = detail;
        this.row = row;
        this.col = col;
    }

    
    public Show() {
    }

    public Show(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public Date getShowTime() {
        return showTime;
    }

    public void setShowTime(Date showTime) {
        this.showTime = showTime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Show)) {
            return false;
        }
        Show other = (Show) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ticketbooking.entity.Show[ id=" + id + " ]";
    }
    
}
