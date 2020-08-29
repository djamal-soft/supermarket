package com.ntic.discovery.dto.command;


public class AddMsCommandDto {

    private int id;
    private String name;
    private String mkeys;
    private String address;
    private float version;
    private String status;

    public AddMsCommandDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMkeys() {
        return mkeys;
    }

    public void setMkeys(String mkeys) {
        this.mkeys = mkeys;
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
        return "AddMsCommandDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mkeys='" + mkeys + '\'' +
                ", address='" + address + '\'' +
                ", version=" + version +
                ", status='" + status + '\'' +
                '}';
    }
}
