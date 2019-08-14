package com.entity;

import com.utils.CodeGeneratorUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "`code`")
public class Code {

    @GeneratedValue
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "value")
    private String value;

    @JoinTable(name = "order_table", joinColumns = {@JoinColumn(name = "id")})
    private Long orderId;

    public Code() {

    }

    public Code(Long orderId) {
        this.value = CodeGeneratorUtil.generateCode();
        this.orderId = orderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCode() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Code code = (Code) o;
        return Objects.equals(id, code.id) &&
                Objects.equals(value, code.value) &&
                Objects.equals(orderId, code.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, orderId);
    }

    @Override
    public String toString() {
        return "Code{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", orderId=" + orderId +
                '}';
    }
}
