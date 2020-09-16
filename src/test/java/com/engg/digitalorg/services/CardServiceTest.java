package com.engg.digitalorg.services;

import com.engg.digitalorg.exception.DigitalOrgException;
import com.engg.digitalorg.managers.CardManager;
import com.engg.digitalorg.model.entity.Card;
import com.engg.digitalorg.model.entity.Icon;
import com.engg.digitalorg.model.request.CardRequest;
import com.engg.digitalorg.model.response.CardResponse;
import com.engg.digitalorg.model.response.IconResponse;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * The type Card service test.
 */
public class CardServiceTest {

    @Mock
    private CardManager mockCardManager;

    @InjectMocks
    private CardService cardServiceUnderTest;

    /**
     * Sets up.
     */
    @BeforeMethod
    public void setUp() {
        initMocks(this);
    }

    /**
     * Test create card.
     *
     * @throws Exception the exception
     */
    @Test
    public void testCreateCard() throws Exception {
        // Setup
        final CardRequest cardRequest = new CardRequest();
        cardRequest.setTitle("title");
        cardRequest.setDescription("description");
        cardRequest.setOriginal_url("original_url");
        cardRequest.setExpire_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        cardRequest.setCreated_by("created_by");
        cardRequest.setTribe("tribe");
        cardRequest.setTeam("team");
        cardRequest.setComponent("component");
        cardRequest.setUpdated_by("updated_by");

        // Configure CardManager.createCard(...).
        final CardResponse response = new CardResponse();
        response.setId(0);
        response.setTitle("title");
        response.setDescription("description");
        response.setOriginal_url("original_url");
        response.setShort_url("short_url");
        response.setExpire_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        response.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        response.setCreated_by("created_by");
        response.setTribe("tribe");
        response.setTeam("team");
        when(mockCardManager.createCard(any(CardRequest.class))).thenReturn(response);

        // Run the test
        final ResponseEntity<CardResponse> result = cardServiceUnderTest.createCard(cardRequest);

        // Verify the results
    }

    /**
     * Test create card card manager throws io exception.
     *
     * @throws Exception the exception
     */
    @Test(expectedExceptions = {IOException.class})
    public void testCreateCard_CardManagerThrowsIOException() throws Exception {
        // Setup
        final CardRequest cardRequest = new CardRequest();
        cardRequest.setTitle("title");
        cardRequest.setDescription("description");
        cardRequest.setOriginal_url("original_url");
        cardRequest.setExpire_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        cardRequest.setCreated_by("created_by");
        cardRequest.setTribe("tribe");
        cardRequest.setTeam("team");
        cardRequest.setComponent("component");
        cardRequest.setUpdated_by("updated_by");

        when(mockCardManager.createCard(any(CardRequest.class))).thenThrow(IOException.class);

        // Run the test
        cardServiceUnderTest.createCard(cardRequest);
    }

    /**
     * Test uplaod image.
     *
     * @throws Exception the exception
     */
    @Test
    public void testUplaodImage() throws Exception {
        // Setup
        final MultipartFile file = null;

        // Run the test
        final ResponseEntity result = cardServiceUnderTest.uplaodImage(0, file);

        // Verify the results
        verify(mockCardManager).uplaodImage(any(Icon.class));
    }

    /**
     * Test uplaod image throws io exception.
     *
     * @throws Exception the exception
     */
    @Test(expectedExceptions = {IOException.class})
    public void testUplaodImage_ThrowsIOException() throws Exception {
        // Setup
        final MultipartFile file = null;

        // Run the test
        cardServiceUnderTest.uplaodImage(0, file);
    }

    /**
     * Test download image.
     *
     * @throws Exception the exception
     */
    @Test
    public void testDownloadImage() throws Exception {
        // Setup

        // Configure CardManager.downloadImage(...).
        final Icon icon = new Icon("originalFilename", "contentType", 0, "content".getBytes());
        when(mockCardManager.downloadImage(0)).thenReturn(icon);

        // Run the test
        final ResponseEntity<IconResponse> result = cardServiceUnderTest.downloadImage(0);

        // Verify the results
    }

    /**
     * Test download image throws io exception.
     *
     * @throws Exception the exception
     */
    @Test(expectedExceptions = {IOException.class})
    public void testDownloadImage_ThrowsIOException() throws Exception {
        // Setup

        // Configure CardManager.downloadImage(...).
        final Icon icon = new Icon("originalFilename", "contentType", 0, "content".getBytes());
        when(mockCardManager.downloadImage(0)).thenReturn(icon);

        // Run the test
        cardServiceUnderTest.downloadImage(0);
    }

    /**
     * Test download imageocta.
     *
     * @throws Exception the exception
     */
    @Test
    public void testDownloadImageocta() throws Exception {
        // Setup

        // Configure CardManager.downloadImage(...).
        final Icon icon = new Icon("originalFilename", "contentType", 0, "content".getBytes());
        when(mockCardManager.downloadImage(0)).thenReturn(icon);

        // Run the test
        final ResponseEntity<Resource> result = cardServiceUnderTest.downloadImageocta(0);

        // Verify the results
    }

    /**
     * Test download imageocta throws io exception.
     *
     * @throws Exception the exception
     */
    @Test(expectedExceptions = {IOException.class})
    public void testDownloadImageocta_ThrowsIOException() throws Exception {
        // Setup

        // Configure CardManager.downloadImage(...).
        final Icon icon = new Icon("originalFilename", "contentType", 0, "content".getBytes());
        when(mockCardManager.downloadImage(0)).thenReturn(icon);

        // Run the test
        cardServiceUnderTest.downloadImageocta(0);
    }

    /**
     * Test delete card.
     */
    @Test
    public void testDeleteCard() {
        // Setup

        // Configure CardManager.getCardById(...).
        final Card card = new Card();
        card.setId(0);
        card.setTitle("title");
        card.setDescription("description");
        card.setUrl_id(12);
        card.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        card.setCreated_by("created_by");
        card.setTribe("tribe");
        card.setTeam("team");
        when(mockCardManager.getCardById(0)).thenReturn(card);

        // Run the test
        final ResponseEntity result = cardServiceUnderTest.deleteCard(0, "email");

        // Verify the results
        verify(mockCardManager).deleteCard(card, "email@emil.com");
    }

    /**
     * Test delete card throws digital org exception.
     */
    @Test(expectedExceptions = {DigitalOrgException.class})
    public void testDeleteCard_ThrowsDigitalOrgException() {
        // Setup

        // Configure CardManager.getCardById(...).
        final Card card = new Card();
        card.setId(0);
        card.setTitle("title");
        card.setDescription("description");
        card.setUrl_id(12);
        card.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        card.setCreated_by("created_by");
        card.setTribe("tribe");
        card.setTeam("team");
        when(mockCardManager.getCardById(0)).thenReturn(card);

        // Run the test
        cardServiceUnderTest.deleteCard(0, "email");
    }

    /**
     * Test get allcard.
     */
    @Test
    public void testGetAllcard() {
        // Setup

        // Configure CardManager.getAllCard(...).
        final CardResponse response = new CardResponse();
        response.setId(0);
        response.setTitle("title");
        response.setDescription("description");
        response.setOriginal_url("original_url");
        response.setShort_url("short_url");
        response.setExpire_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        response.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        response.setCreated_by("created_by");
        response.setTribe("tribe");
        response.setTeam("team");
        final List<CardResponse> cardResponses = Arrays.asList(response);
        when(mockCardManager.getAllCard("emailId")).thenReturn(cardResponses);

        // Run the test
        final ResponseEntity<List> result = cardServiceUnderTest.getAllcard("email");

        // Verify the results
    }

    /**
     * Test get allcard throws digital org exception.
     */
    @Test(expectedExceptions = {DigitalOrgException.class})
    public void testGetAllcard_ThrowsDigitalOrgException() {
        // Setup

        // Configure CardManager.getAllCard(...).
        final CardResponse response = new CardResponse();
        response.setId(0);
        response.setTitle("title");
        response.setDescription("description");
        response.setOriginal_url("original_url");
        response.setShort_url("short_url");
        response.setExpire_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        response.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        response.setCreated_by("created_by");
        response.setTribe("tribe");
        response.setTeam("team");
        final List<CardResponse> cardResponses = Arrays.asList(response);
        when(mockCardManager.getAllCard("emailId")).thenReturn(cardResponses);

        // Run the test
        cardServiceUnderTest.getAllcard("email");
    }
}
