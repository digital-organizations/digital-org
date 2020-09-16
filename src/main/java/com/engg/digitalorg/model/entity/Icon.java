package com.engg.digitalorg.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name="icon", schema = "digital")
public class Icon {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String name;
    private String type;
    private int card_id;
    @Lob
    private byte[] file;

    public Icon() {

    }

    public Icon(String originalFilename, String contentType, int cardId, byte[] compressBytes ) {
        this.name = originalFilename;
        this.type = contentType;
        this.card_id = cardId;
        this.file = compressBytes;
    }
}
