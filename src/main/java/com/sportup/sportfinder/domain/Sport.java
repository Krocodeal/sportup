package com.sportup.sportfinder.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Sport.
 */
@Entity
@Table(name = "sport")
public class Sport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    @Column(name = "to_muscler")
    private Boolean toMuscler;

    @Column(name = "to_secher")
    private Boolean toSecher;

    @Column(name = "is_collectif")
    private Boolean isCollectif;

    @Column(name = "to_cible_haut")
    private Boolean toCibleHaut;

    @Column(name = "to_cible_bas")
    private Boolean toCibleBas;

    @Column(name = "is_contact")
    private Boolean isContact;

    @Column(name = "is_balle")
    private Boolean isBalle;

    @Column(name = "is_intense")
    private Boolean isIntense;

    @Column(name = "is_combat")
    private Boolean isCombat;

    @Column(name = "is_percussion")
    private Boolean isPercussion;

    @Column(name = "is_cher")
    private Boolean isCher;

    @Column(name = "is_artistique")
    private Boolean isArtistique;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Sport nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public byte[] getImage() {
        return image;
    }

    public Sport image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public Sport imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public Boolean isToMuscler() {
        return toMuscler;
    }

    public Sport toMuscler(Boolean toMuscler) {
        this.toMuscler = toMuscler;
        return this;
    }

    public void setToMuscler(Boolean toMuscler) {
        this.toMuscler = toMuscler;
    }

    public Boolean isToSecher() {
        return toSecher;
    }

    public Sport toSecher(Boolean toSecher) {
        this.toSecher = toSecher;
        return this;
    }

    public void setToSecher(Boolean toSecher) {
        this.toSecher = toSecher;
    }

    public Boolean isIsCollectif() {
        return isCollectif;
    }

    public Sport isCollectif(Boolean isCollectif) {
        this.isCollectif = isCollectif;
        return this;
    }

    public void setIsCollectif(Boolean isCollectif) {
        this.isCollectif = isCollectif;
    }

    public Boolean isToCibleHaut() {
        return toCibleHaut;
    }

    public Sport toCibleHaut(Boolean toCibleHaut) {
        this.toCibleHaut = toCibleHaut;
        return this;
    }

    public void setToCibleHaut(Boolean toCibleHaut) {
        this.toCibleHaut = toCibleHaut;
    }

    public Boolean isToCibleBas() {
        return toCibleBas;
    }

    public Sport toCibleBas(Boolean toCibleBas) {
        this.toCibleBas = toCibleBas;
        return this;
    }

    public void setToCibleBas(Boolean toCibleBas) {
        this.toCibleBas = toCibleBas;
    }

    public Boolean isIsContact() {
        return isContact;
    }

    public Sport isContact(Boolean isContact) {
        this.isContact = isContact;
        return this;
    }

    public void setIsContact(Boolean isContact) {
        this.isContact = isContact;
    }

    public Boolean isIsBalle() {
        return isBalle;
    }

    public Sport isBalle(Boolean isBalle) {
        this.isBalle = isBalle;
        return this;
    }

    public void setIsBalle(Boolean isBalle) {
        this.isBalle = isBalle;
    }

    public Boolean isIsIntense() {
        return isIntense;
    }

    public Sport isIntense(Boolean isIntense) {
        this.isIntense = isIntense;
        return this;
    }

    public void setIsIntense(Boolean isIntense) {
        this.isIntense = isIntense;
    }

    public Boolean isIsCombat() {
        return isCombat;
    }

    public Sport isCombat(Boolean isCombat) {
        this.isCombat = isCombat;
        return this;
    }

    public void setIsCombat(Boolean isCombat) {
        this.isCombat = isCombat;
    }

    public Boolean isIsPercussion() {
        return isPercussion;
    }

    public Sport isPercussion(Boolean isPercussion) {
        this.isPercussion = isPercussion;
        return this;
    }

    public void setIsPercussion(Boolean isPercussion) {
        this.isPercussion = isPercussion;
    }

    public Boolean isIsCher() {
        return isCher;
    }

    public Sport isCher(Boolean isCher) {
        this.isCher = isCher;
        return this;
    }

    public void setIsCher(Boolean isCher) {
        this.isCher = isCher;
    }

    public Boolean isIsArtistique() {
        return isArtistique;
    }

    public Sport isArtistique(Boolean isArtistique) {
        this.isArtistique = isArtistique;
        return this;
    }

    public void setIsArtistique(Boolean isArtistique) {
        this.isArtistique = isArtistique;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sport sport = (Sport) o;
        if (sport.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sport.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Sport{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", image='" + getImage() + "'" +
            ", imageContentType='" + imageContentType + "'" +
            ", toMuscler='" + isToMuscler() + "'" +
            ", toSecher='" + isToSecher() + "'" +
            ", isCollectif='" + isIsCollectif() + "'" +
            ", toCibleHaut='" + isToCibleHaut() + "'" +
            ", toCibleBas='" + isToCibleBas() + "'" +
            ", isContact='" + isIsContact() + "'" +
            ", isBalle='" + isIsBalle() + "'" +
            ", isIntense='" + isIsIntense() + "'" +
            ", isCombat='" + isIsCombat() + "'" +
            ", isPercussion='" + isIsPercussion() + "'" +
            ", isCher='" + isIsCher() + "'" +
            ", isArtistique='" + isIsArtistique() + "'" +
            "}";
    }
}
