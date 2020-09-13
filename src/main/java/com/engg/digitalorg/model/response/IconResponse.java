package com.engg.digitalorg.model.response;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class IconResponse {

    private int id;
    private String name;
    private String type;
    private int cardId;
    private byte[] file;

    public IconResponse(String originalFilename, String contentType, byte[] compressBytes, int cardId, int id) {
        this.name = originalFilename;
        this.type = contentType;
        this.file = compressBytes;
        this.cardId = cardId;
        this.id = id;
    }
}
