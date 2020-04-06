package com.mycompany.myapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

import com.mycompany.myapp.domain.enumeration.StatusRequest;

/**
 * A Request.
 */
@Document(collection = "request")
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("request_id")
    private String requestID;

    @Field("status")
    private StatusRequest status;

    @Field("error")
    private String error;

    @DBRef
    @Field("order")
    private Set<Order> orders = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequestID() {
        return requestID;
    }

    public Request requestID(String requestID) {
        this.requestID = requestID;
        return this;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public StatusRequest getStatus() {
        return status;
    }

    public Request status(StatusRequest status) {
        this.status = status;
        return this;
    }

    public void setStatus(StatusRequest status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public Request error(String error) {
        this.error = error;
        return this;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public Request orders(Set<Order> orders) {
        this.orders = orders;
        return this;
    }

    public Request addOrder(Order order) {
        this.orders.add(order);
        order.setRequest(this);
        return this;
    }

    public Request removeOrder(Order order) {
        this.orders.remove(order);
        order.setRequest(null);
        return this;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Request)) {
            return false;
        }
        return id != null && id.equals(((Request) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Request{" +
            "id=" + getId() +
            ", requestID='" + getRequestID() + "'" +
            ", status='" + getStatus() + "'" +
            ", error='" + getError() + "'" +
            "}";
    }
}
