/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketbooking.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author baojiaang
 */
@Entity
@Table(name = "SEAT")
@NamedQueries({
    @NamedQuery(name = "Seat.findAll", query = "SELECT s FROM Seat s")})
public class Seat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "SHOW_ID")
    private Integer showId;
    @Column(name = "SEAT_ROW_NUMBER")
    private Integer seatRowNumber;
    @Column(name = "SEAT_COLUMN_NUMBER")
    private Integer seatColumnNumber;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "TICKET_ID")
    private Integer ticketId;
    @Column(name = "STATUS")
    private Short status;
    @Column(name = "RANK")
    private Short rank;

    public Seat(Integer id, Integer showId, Integer seatRowNumber, Integer seatColumnNumber, Double price, Integer ticketId, Short status, Short rank) {
        this.id = id;
        this.showId = showId;
        this.seatRowNumber = seatRowNumber;
        this.seatColumnNumber = seatColumnNumber;
        this.price = price;
        this.ticketId = ticketId;
        this.status = status;
        this.rank = rank;
    }

    public Seat(Integer showId, Integer seatRowNumber, Integer seatColumnNumber, Double price, Integer ticketId, Short status, Short rank) {
  
        this.showId = showId;
        this.seatRowNumber = seatRowNumber;
        this.seatColumnNumber = seatColumnNumber;
        this.price = price;
        this.ticketId = ticketId;
        this.status = status;
        this.rank = rank;
    }

    public Seat() {
    }

    public Seat(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShowId() {
        return showId;
    }

    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    public Integer getSeatRowNumber() {
        return seatRowNumber;
    }

    public void setSeatRowNumber(Integer seatRowNumber) {
        this.seatRowNumber = seatRowNumber;
    }

    public Integer getSeatColumnNumber() {
        return seatColumnNumber;
    }

    public void setSeatColumnNumber(Integer seatColumnNumber) {
        this.seatColumnNumber = seatColumnNumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Short getRank() {
        return rank;
    }

    public void setRank(Short rank) {
        this.rank = rank;
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
        if (!(object instanceof Seat)) {
            return false;
        }
        Seat other = (Seat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ticketbooking.entity.Seat[ id=" + id + " ]";
    }
    
}
