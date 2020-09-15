package com.engg.digitalorg.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Lob;
@Getter
@Setter
@Entity
@Table(name="icon", schema = "org")
public class Icon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String type;
    private int card_id;
    @Lob
    private byte[] file;

    public Icon() {

    }

    public Icon(String originalFilename, String contentType, byte[] compressBytes, int cardId) {
        this.name = originalFilename;
        this.type = contentType;
        this.file = compressBytes;
        this.card_id = cardId;
    }
}
