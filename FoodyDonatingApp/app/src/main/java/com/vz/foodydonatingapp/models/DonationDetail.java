package com.vz.foodydonatingapp.models;

public class DonationDetail {
    String pickupAddress,items_Detail,pick_up_time,pick_up_day,ngoName,mobile,donarName;
    String donationId;

    public String getDonationId() {
        return donationId;
    }

    public void setDonationId(String donationId) {
        this.donationId = donationId;
    }

    String donateTime;

    public String getDonateTime() {
        return donateTime;
    }

    public void setDonateTime(String donateTime) {
        this.donateTime = donateTime;
    }

    public DonationDetail(String pickupAddress, String items_Detail, String pick_up_time, String pick_up_day, String ngoName) {
        this.pickupAddress = pickupAddress;
        this.items_Detail = items_Detail;
        this.pick_up_time = pick_up_time;
        this.pick_up_day = pick_up_day;
        this.ngoName = ngoName;
    }

    public DonationDetail() {
    }



    public DonationDetail(String pickupAddress, String items_Detail, String pick_up_time, String pick_up_day, String ngoName,
                          String mobile, String donarName) {
        this.pickupAddress = pickupAddress;
        this.items_Detail = items_Detail;
        this.pick_up_time = pick_up_time;
        this.pick_up_day = pick_up_day;
        this.ngoName = ngoName;
        this.mobile = mobile;
        this.donarName = donarName;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getItems_Detail() {
        return items_Detail;
    }

    public void setItems_Detail(String items_Detail) {
        this.items_Detail = items_Detail;
    }

    public String getPick_up_time() {
        return pick_up_time;
    }

    public void setPick_up_time(String pick_up_time) {
        this.pick_up_time = pick_up_time;
    }

    public String getPick_up_day() {
        return pick_up_day;
    }

    public void setPick_up_day(String pick_up_day) {
        this.pick_up_day = pick_up_day;
    }

    public String getNgoName() {
        return ngoName;
    }

    public void setNgoName(String ngoName) {
        this.ngoName = ngoName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDonarName() {
        return donarName;
    }

    public void setDonarName(String donarName) {
        this.donarName = donarName;
    }
}
