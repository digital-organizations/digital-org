package com.engg.digitalorg.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="icon", schema = "digital")
public class Icon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String type;
    private int cardId;
    @Lob
    private byte[] file;

    public Icon(String originalFilename, String contentType, byte[] compressBytes, int cardId) {
        this.name = originalFilename;
        this.type = contentType;
        this.file = compressBytes;
        this.cardId = cardId;
    }
}
