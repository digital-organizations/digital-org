package com.engg.digitalorg.model.response;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Icon response.
 */
@Getter
@Setter
public class IconResponse {

    private int id;
    private String name;
    private String type;
    private int cardId;
    private byte[] file;

    /**
     * Instantiates a new Icon response.
     *
     * @param originalFilename the original filename
     * @param contentType      the content type
     * @param compressBytes    the compress bytes
     * @param cardId           the card id
     * @param id               the id
     */
    public IconResponse(String originalFilename, String contentType, byte[] compressBytes, int cardId, int id) {
        this.name = originalFilename;
        this.type = contentType;
        this.file = compressBytes;
        this.cardId = cardId;
        this.id = id;
    }
}
