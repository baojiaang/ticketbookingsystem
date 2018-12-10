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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author baojiaang
 */
@Entity
@Table(name = "ORDER_INFO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderInfo.findAll", query = "SELECT o FROM OrderInfo o")
    , @NamedQuery(name = "OrderInfo.findById", query = "SELECT o FROM OrderInfo o WHERE o.id = :id")
    , @NamedQuery(name = "OrderInfo.findByUserId", query = "SELECT o FROM OrderInfo o WHERE o.userId = :userId")
    , @NamedQuery(name = "OrderInfo.findByAmount", query = "SELECT o FROM OrderInfo o WHERE o.amount = :amount")
    , @NamedQuery(name = "OrderInfo.findByPrice", query = "SELECT o FROM OrderInfo o WHERE o.price = :price")
    , @NamedQuery(name = "OrderInfo.findByOrderDate", query = "SELECT o FROM OrderInfo o WHERE o.orderDate = :orderDate")
    , @NamedQuery(name = "OrderInfo.findByTicketId", query = "SELECT o FROM OrderInfo o WHERE o.ticketId = :ticketId")
    , @NamedQuery(name = "OrderInfo.findByShowId", query = "SELECT o FROM OrderInfo o WHERE o.showId = :showId")
    , @NamedQuery(name = "OrderInfo.findBySeatId", query = "SELECT o FROM OrderInfo o WHERE o.seatId = :seatId")
    , @NamedQuery(name = "OrderInfo.findByOrderTime", query = "SELECT o FROM OrderInfo o WHERE o.orderTime = :orderTime")})
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "USER_ID")
    private Integer userId;
    @Column(name = "AMOUNT")
    private Integer amount;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "ORDER_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Column(name = "TICKET_ID")
    private Integer ticketId;
    @Column(name = "SHOW_ID")
    private Integer showId;
    @Column(name = "SEAT_ID")
    private Integer seatId;
    @Column(name = "ORDER_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderTime;

    public OrderInfo() {
    }

    public OrderInfo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getShowId() {
        return showId;
    }

    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
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
        if (!(object instanceof OrderInfo)) {
            return false;
        }
        OrderInfo other = (OrderInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ticketbooking.entity.OrderInfo[ id=" + id + " ]";
    }
    
}
