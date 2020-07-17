package com.ntic.discovery.dto.command;

public class DeleteMsCommandDto {

    private int id;

    public DeleteMsCommandDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DeleteMsCommandDto{" +
                "id=" + id +
                '}';
    }
}
