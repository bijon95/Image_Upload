package info.bijon.image_upload;

/**
 * Created by Bj on 10-06-17.
 */

public class SummaryGetSet {

    private String wardno;
     private String noOfSpot;
     private String clear;
     private String unClear;
     private String remark;

    public SummaryGetSet(String wardno, String noOfSpot, String clear, String unClear, String remark) {
        this.wardno = wardno;
        this.noOfSpot = noOfSpot;
        this.clear = clear;
        this.unClear = unClear;
        this.remark = remark;

    }

    public String getWardno() {
        return wardno;
    }

    public void setWardno(String wardno) {
        this.wardno = wardno;
    }

    public String getNoOfSpot() {
        return noOfSpot;
    }

    public void setNoOfSpot(String noOfSpot) {
        this.noOfSpot = noOfSpot;
    }

    public String getClear() {
        return clear;
    }

    public void setClear(String clear) {
        this.clear = clear;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUnClear() {
        return unClear;
    }

    public void setUnClear(String unClear) {
        this.unClear = unClear;
    }
}