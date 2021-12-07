package org.acme.DTO;

public class Service3DTO {

    int prix;
    int code_postal;
    int superficie;

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(int code_postal) {
        this.code_postal = code_postal;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }


    @Override
    public String toString() {
        return "Service3DTO{" +
                "prix=" + prix +
                ", code_postal=" + code_postal +
                ", superficie=" + superficie +
                '}';
    }
}
