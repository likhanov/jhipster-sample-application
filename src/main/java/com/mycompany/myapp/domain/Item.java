package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Item.
 */
@Document(collection = "item")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("p_poid")
    private String pPOID;

    @Field("label_id")
    private String labelID;

    @Field("t_d_code")
    private String tDCode;

    @Field("quantity")
    private Long quantity;

    @Field("sales_country_name")
    private String salesCountryName;

    @Field("comment")
    private String comment;

    @Field("sticker_id")
    private String stickerID;

    @DBRef
    @Field("order")
    @JsonIgnoreProperties("items")
    private Order order;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpPOID() {
        return pPOID;
    }

    public Item pPOID(String pPOID) {
        this.pPOID = pPOID;
        return this;
    }

    public void setpPOID(String pPOID) {
        this.pPOID = pPOID;
    }

    public String getLabelID() {
        return labelID;
    }

    public Item labelID(String labelID) {
        this.labelID = labelID;
        return this;
    }

    public void setLabelID(String labelID) {
        this.labelID = labelID;
    }

    public String gettDCode() {
        return tDCode;
    }

    public Item tDCode(String tDCode) {
        this.tDCode = tDCode;
        return this;
    }

    public void settDCode(String tDCode) {
        this.tDCode = tDCode;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Item quantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getSalesCountryName() {
        return salesCountryName;
    }

    public Item salesCountryName(String salesCountryName) {
        this.salesCountryName = salesCountryName;
        return this;
    }

    public void setSalesCountryName(String salesCountryName) {
        this.salesCountryName = salesCountryName;
    }

    public String getComment() {
        return comment;
    }

    public Item comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStickerID() {
        return stickerID;
    }

    public Item stickerID(String stickerID) {
        this.stickerID = stickerID;
        return this;
    }

    public void setStickerID(String stickerID) {
        this.stickerID = stickerID;
    }

    public Order getOrder() {
        return order;
    }

    public Item order(Order order) {
        this.order = order;
        return this;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }
        return id != null && id.equals(((Item) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Item{" +
            "id=" + getId() +
            ", pPOID='" + getpPOID() + "'" +
            ", labelID='" + getLabelID() + "'" +
            ", tDCode='" + gettDCode() + "'" +
            ", quantity=" + getQuantity() +
            ", salesCountryName='" + getSalesCountryName() + "'" +
            ", comment='" + getComment() + "'" +
            ", stickerID='" + getStickerID() + "'" +
            "}";
    }
}
