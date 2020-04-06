package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A Order.
 */
@Document(collection = "order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("order_id")
    private String orderID;

    @Field("version")
    private Integer version;

    @Field("collection_name")
    private String collectionName;

    @Field("is_cancelled")
    private Boolean isCancelled;

    @Field("nominee_name")
    private String nomineeName;

    @Field("vendor_name")
    private String vendorName;

    @Field("factory_name")
    private String factoryName;

    @Field("delivery_address")
    private String deliveryAddress;

    @Field("comment")
    private String comment;

    @Field("c_il_quantity")
    private Long cILQuantity;

    @Field("c_il_request_quantity")
    private Long cILRequestQuantity;

    @DBRef
    @Field("item")
    private Set<Item> items = new HashSet<>();

    @DBRef
    @Field("request")
    @JsonIgnoreProperties("orders")
    private Request request;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderID() {
        return orderID;
    }

    public Order orderID(String orderID) {
        this.orderID = orderID;
        return this;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Integer getVersion() {
        return version;
    }

    public Order version(Integer version) {
        this.version = version;
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public Order collectionName(String collectionName) {
        this.collectionName = collectionName;
        return this;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public Boolean isIsCancelled() {
        return isCancelled;
    }

    public Order isCancelled(Boolean isCancelled) {
        this.isCancelled = isCancelled;
        return this;
    }

    public void setIsCancelled(Boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public String getNomineeName() {
        return nomineeName;
    }

    public Order nomineeName(String nomineeName) {
        this.nomineeName = nomineeName;
        return this;
    }

    public void setNomineeName(String nomineeName) {
        this.nomineeName = nomineeName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public Order vendorName(String vendorName) {
        this.vendorName = vendorName;
        return this;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public Order factoryName(String factoryName) {
        this.factoryName = factoryName;
        return this;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public Order deliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
        return this;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getComment() {
        return comment;
    }

    public Order comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getcILQuantity() {
        return cILQuantity;
    }

    public Order cILQuantity(Long cILQuantity) {
        this.cILQuantity = cILQuantity;
        return this;
    }

    public void setcILQuantity(Long cILQuantity) {
        this.cILQuantity = cILQuantity;
    }

    public Long getcILRequestQuantity() {
        return cILRequestQuantity;
    }

    public Order cILRequestQuantity(Long cILRequestQuantity) {
        this.cILRequestQuantity = cILRequestQuantity;
        return this;
    }

    public void setcILRequestQuantity(Long cILRequestQuantity) {
        this.cILRequestQuantity = cILRequestQuantity;
    }

    public Set<Item> getItems() {
        return items;
    }

    public Order items(Set<Item> items) {
        this.items = items;
        return this;
    }

    public Order addItem(Item item) {
        this.items.add(item);
        item.setOrder(this);
        return this;
    }

    public Order removeItem(Item item) {
        this.items.remove(item);
        item.setOrder(null);
        return this;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Request getRequest() {
        return request;
    }

    public Order request(Request request) {
        this.request = request;
        return this;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }
        return id != null && id.equals(((Order) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Order{" +
            "id=" + getId() +
            ", orderID='" + getOrderID() + "'" +
            ", version=" + getVersion() +
            ", collectionName='" + getCollectionName() + "'" +
            ", isCancelled='" + isIsCancelled() + "'" +
            ", nomineeName='" + getNomineeName() + "'" +
            ", vendorName='" + getVendorName() + "'" +
            ", factoryName='" + getFactoryName() + "'" +
            ", deliveryAddress='" + getDeliveryAddress() + "'" +
            ", comment='" + getComment() + "'" +
            ", cILQuantity=" + getcILQuantity() +
            ", cILRequestQuantity=" + getcILRequestQuantity() +
            "}";
    }
}
