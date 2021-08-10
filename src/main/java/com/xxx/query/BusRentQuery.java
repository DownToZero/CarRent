package com.xxx.query;


import com.xxx.base.BaseQuery;

public class BusRentQuery extends BaseQuery {
    private String rentid;
    private String identity;
    private String carnumber;
    private String begindate;
    private String returndate;

    public BusRentQuery() {
    }

    public BusRentQuery(String rentid, String identity, String carnumber, String begindate, String returndate) {
        this.rentid = rentid;
        this.identity = identity;
        this.carnumber = carnumber;
        this.begindate = begindate;
        this.returndate = returndate;
    }

    public String getRentid() {
        return rentid;
    }

    public void setRentid(String rentid) {
        this.rentid = rentid;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getCarnumber() {
        return carnumber;
    }

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber;
    }

    public String getBegindate() {
        return begindate;
    }

    public void setBegindate(String begindate) {
        this.begindate = begindate;
    }

    public String getReturndate() {
        return returndate;
    }

    public void setReturndate(String returndate) {
        this.returndate = returndate;
    }

    @Override
    public String toString() {
        return "BusRentQuery{" +
                "rentid='" + rentid + '\'' +
                ", identity='" + identity + '\'' +
                ", carnumber='" + carnumber + '\'' +
                ", begindate='" + begindate + '\'' +
                ", returndate='" + returndate + '\'' +
                '}';
    }
}
