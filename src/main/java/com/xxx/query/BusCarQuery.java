package com.xxx.query;

import com.xxx.base.BaseQuery;

public class BusCarQuery extends BaseQuery {
    private String carnumber;
    private String cartype;
    private String color;
    private String description;
    private Integer isrenting;

    public BusCarQuery() {
    }

    public String getCarnumber() {
        return carnumber;
    }

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber;
    }

    public String getCartype() {
        return cartype;
    }

    public void setCartype(String cartype) {
        this.cartype = cartype;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsrenting() {
        return isrenting;
    }

    public void setIsrenting(Integer isrenting) {
        this.isrenting = isrenting;
    }

    @Override
    public String toString() {
        return "BusCarQuery{" +
                "carnumber='" + carnumber + '\'' +
                ", cartype='" + cartype + '\'' +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                ", isrenting=" + isrenting +
                '}';
    }
}
