package info.bijon.image_upload;

/**
 * Created by Bj on 30-05-17.
 */

public class Product {

    private String image;
    private String name;
    private String price;
   private String latitude;
   private String longitude;
    private String remark;



    public Product( String image, String name, String price, String latitude, String longitude,String remark) {
        this.image = image;
        this.name = name;
        this.price = price;

        this.latitude = latitude;
        this.longitude = longitude;
        this.remark=remark;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}
