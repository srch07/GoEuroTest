package com.go_euro.test.vo;

/**
 * Created by Abhishek Anand on 1/28/2016.
 */
public class OutputVO {

    private String _id;
    private String name;
    private String type;
    private String latitude;
    private String longitude;

    public OutputVO(String _id, String name, String type, String latitude, String longitude) {
        this._id = _id;
        this.name = name;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
    public String getCSVFormat(){
        String CSV_SEPERATOR = ",";
        String LINE_SPERATOR = System.getProperty("line.separator");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(_id).append(CSV_SEPERATOR).append(name).append(CSV_SEPERATOR).append(type).append(CSV_SEPERATOR)
                .append(latitude).append(CSV_SEPERATOR).append(longitude).append(LINE_SPERATOR);
        return stringBuffer.toString();
    }
    @Override
    public String toString() {
        return "OutputVO{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
