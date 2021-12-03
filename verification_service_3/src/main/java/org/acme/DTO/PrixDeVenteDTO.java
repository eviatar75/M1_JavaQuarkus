package org.acme.DTO;

public class PrixDeVenteDTO {
    int code_postal;
    int prix;
    int superficie;

    public PrixDeVenteDTO() {
    }

    public int getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(int code_postal) {
        this.code_postal = code_postal;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    @Override
    public String toString(){
        return ("Le prix de vente de ce bien immobilier est de "
                + this.getPrix() +
                " situé dans le "+ this.getCode_postal()
                +"avec une superficie de "
                +this.getSuperficie()
                );
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public int getPrixM2(){
        return (this.getPrix()/this.getSuperficie());
    }
}
