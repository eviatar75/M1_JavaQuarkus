package org.acme.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Prix_lieu_dit")
@Entity
public class PrixLieuDit {
    @Id
    @Column(name = "code_postal", nullable = false, length = 5)
    private String id;

    @Column(name = "prix_min_m2", nullable = false)
    private Double prixMinM2;

    public Double getPrixMinM2() {
        return prixMinM2;
    }

    public void setPrixMinM2(Double prixMinM2) {
        this.prixMinM2 = prixMinM2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}