package com.ntic.discovery.dto.query;

public class MsQueryDto {

    private int id;
    private String name;
    private String keys;
    private String address;
    private float version;
    private String status;


    public MsQueryDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getVersion() {
        return version;
    }

    public void setVersion(float version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MsQueryDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", keys='" + keys + '\'' +
                ", address='" + address + '\'' +
                ", version=" + version +
                ", status='" + status + '\'' +
                '}';
    }
}
