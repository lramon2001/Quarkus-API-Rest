package com.stefanini.entity;

public class ProvedorEmail {
    private String provedor;
    private int id;

    public String getProvedor() {
        return provedor;
    }

    public void setProvedor(String provedor) {
        this.provedor = provedor;
    }

    public ProvedorEmail(String provedor) {
        this.provedor = provedor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProvedorEmail(String provedor, int id) {
        this.provedor = provedor;
        this.id = id;
    }

    public ProvedorEmail() {
    }

    

    

    

    
}
