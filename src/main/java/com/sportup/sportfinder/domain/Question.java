package com.sportup.sportfinder.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Question.
 */
@Entity
@Table(name = "question")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

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

    @OneToMany(mappedBy = "question")
    @JsonIgnore
    private Set<Reponse> responses = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public Question libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Boolean isToMuscler() {
        return toMuscler;
    }

    public Question toMuscler(Boolean toMuscler) {
        this.toMuscler = toMuscler;
        return this;
    }

    public void setToMuscler(Boolean toMuscler) {
        this.toMuscler = toMuscler;
    }

    public Boolean isToSecher() {
        return toSecher;
    }

    public Question toSecher(Boolean toSecher) {
        this.toSecher = toSecher;
        return this;
    }

    public void setToSecher(Boolean toSecher) {
        this.toSecher = toSecher;
    }

    public Boolean isIsCollectif() {
        return isCollectif;
    }

    public Question isCollectif(Boolean isCollectif) {
        this.isCollectif = isCollectif;
        return this;
    }

    public void setIsCollectif(Boolean isCollectif) {
        this.isCollectif = isCollectif;
    }

    public Boolean isToCibleHaut() {
        return toCibleHaut;
    }

    public Question toCibleHaut(Boolean toCibleHaut) {
        this.toCibleHaut = toCibleHaut;
        return this;
    }

    public void setToCibleHaut(Boolean toCibleHaut) {
        this.toCibleHaut = toCibleHaut;
    }

    public Boolean isToCibleBas() {
        return toCibleBas;
    }

    public Question toCibleBas(Boolean toCibleBas) {
        this.toCibleBas = toCibleBas;
        return this;
    }

    public void setToCibleBas(Boolean toCibleBas) {
        this.toCibleBas = toCibleBas;
    }

    public Boolean isIsContact() {
        return isContact;
    }

    public Question isContact(Boolean isContact) {
        this.isContact = isContact;
        return this;
    }

    public void setIsContact(Boolean isContact) {
        this.isContact = isContact;
    }

    public Boolean isIsBalle() {
        return isBalle;
    }

    public Question isBalle(Boolean isBalle) {
        this.isBalle = isBalle;
        return this;
    }

    public void setIsBalle(Boolean isBalle) {
        this.isBalle = isBalle;
    }

    public Boolean isIsIntense() {
        return isIntense;
    }

    public Question isIntense(Boolean isIntense) {
        this.isIntense = isIntense;
        return this;
    }

    public void setIsIntense(Boolean isIntense) {
        this.isIntense = isIntense;
    }

    public Boolean isIsCombat() {
        return isCombat;
    }

    public Question isCombat(Boolean isCombat) {
        this.isCombat = isCombat;
        return this;
    }

    public void setIsCombat(Boolean isCombat) {
        this.isCombat = isCombat;
    }

    public Boolean isIsPercussion() {
        return isPercussion;
    }

    public Question isPercussion(Boolean isPercussion) {
        this.isPercussion = isPercussion;
        return this;
    }

    public void setIsPercussion(Boolean isPercussion) {
        this.isPercussion = isPercussion;
    }

    public Boolean isIsCher() {
        return isCher;
    }

    public Question isCher(Boolean isCher) {
        this.isCher = isCher;
        return this;
    }

    public void setIsCher(Boolean isCher) {
        this.isCher = isCher;
    }

    public Boolean isIsArtistique() {
        return isArtistique;
    }

    public Question isArtistique(Boolean isArtistique) {
        this.isArtistique = isArtistique;
        return this;
    }

    public void setIsArtistique(Boolean isArtistique) {
        this.isArtistique = isArtistique;
    }

    public Set<Reponse> getResponses() {
        return responses;
    }

    public Question responses(Set<Reponse> reponses) {
        this.responses = reponses;
        return this;
    }

    public Question addResponse(Reponse reponse) {
        this.responses.add(reponse);
        reponse.setQuestion(this);
        return this;
    }

    public Question removeResponse(Reponse reponse) {
        this.responses.remove(reponse);
        reponse.setQuestion(null);
        return this;
    }

    public void setResponses(Set<Reponse> reponses) {
        this.responses = reponses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Question question = (Question) o;
        if (question.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), question.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Question{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
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
