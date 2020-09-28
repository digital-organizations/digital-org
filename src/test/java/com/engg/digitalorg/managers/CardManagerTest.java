package com.engg.digitalorg.managers;

import com.engg.digitalorg.model.entity.Card;
import com.engg.digitalorg.model.entity.CardInGroup;
import com.engg.digitalorg.model.entity.Icon;
import com.engg.digitalorg.model.request.CardRequest;
import com.engg.digitalorg.model.response.CardResponse;
import com.engg.digitalorg.repository.CardInGroupRepository;
import com.engg.digitalorg.repository.CardRepository;
import com.engg.digitalorg.repository.IconRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * The type Card manager test.
 */
public class CardManagerTest {

    @Mock
    private CardRepository mockCardRepository;

    @Mock
    private CardInGroupRepository mockCardInGroupRepository;

    @Mock
    private IconRepository mockIconRepository;

    @InjectMocks
    private CardManager cardManagerUnderTest;

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

        final CardInGroup cardInGroup = new CardInGroup();
        List<CardInGroup> cardInGroups = new ArrayList<>();

        // Setup
        final CardRequest cardRequest = new CardRequest();
        cardRequest.setTitle("title");
        cardRequest.setDescription("description");
        cardRequest.setOriginal_url("https://codecov.io/gh/digital-organizations/digital-org/compare/bec00b2af2f45c2f58557caee4f570e41973a2a5...dc5db77e4d5935e56ef79224a0da95ad4e184fa0/tree");
        cardRequest.setExpire_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        cardRequest.setCreated_by("user@gmail.com");
        cardRequest.setTribe("tribe");
        cardRequest.setTeam("team");
        cardRequest.setComponent("component");
        cardRequest.setUpdated_by("user@gmail.com");

        // Configure CardRepository.save(...).
        final Card card = new Card();
        card.setId(0);
        card.setTitle("title");
        card.setDescription("description");
        card.setUrl_id(23);
        card.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        card.setCreated_by("user@gmail.com");
        card.setTribe("tribe");
        card.setTeam("team");
        when(mockCardRepository.save(any(Card.class))).thenReturn(card);
        when(mockCardInGroupRepository.fetchGrupByCardId(card.getId())).thenReturn(cardInGroups);


        // Run the test
        final CardResponse result = cardManagerUnderTest.createCard(cardRequest);

        // Verify the results
    }

    /**
     * Test create card throws io exception.
     *
     * @throws Exception the exception
     */
    @Test(expectedExceptions = {IOException.class})
    public void testCreateCard_ThrowsIOException() throws Exception {
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

        // Configure CardRepository.save(...).
        final Card card = new Card();
        card.setId(0);
        card.setTitle("title");
        card.setDescription("description");
        card.setUrl_id(3);
        card.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        card.setCreated_by("created_by");
        card.setTribe("tribe");
        card.setTeam("team");
        when(mockCardRepository.save(any(Card.class))).thenReturn(card);

        // Run the test
        cardManagerUnderTest.createCard(cardRequest);
    }

    /**
     * Test get card by id.
     */
    @Test
    public void testGetCardById() {
        // Setup

        // Configure CardRepository.findById(...).
        final Card card1 = new Card();
        card1.setId(0);
        card1.setTitle("title");
        card1.setDescription("description");
        card1.setUrl_id(90);
        card1.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        card1.setCreated_by("user@gmail.com");
        card1.setTribe("tribe");
        card1.setTeam("team");
        final Optional<Card> card = Optional.of(card1);
        when(mockCardRepository.findById(0)).thenReturn(card);

        // Run the test
        final Card result = cardManagerUnderTest.getCardById(0);

        // Verify the results
    }

    /**
     * Test uplaod image.
     */
    @Test
    public void testUplaodImage() {
        // Setup
        final Icon icon = new Icon("originalFilename", "contentType", 0, "content".getBytes());

        // Configure IconRepository.save(...).
        final Icon icon1 = new Icon("originalFilename", "contentType", 0, "content".getBytes());
        when(mockIconRepository.save(any(Icon.class))).thenReturn(icon1);

        // Run the test
        cardManagerUnderTest.uplaodImage(icon);

        // Verify the results
    }

    /**
     * Test download image.
     */
    @Test
    public void testDownloadImage() {
        // Setup

        // Configure IconRepository.findById(...).
        final Optional<Icon> icon = Optional.of(new Icon("originalFilename", "contentType", 0, "content".getBytes()));
        when(mockIconRepository.findById(0)).thenReturn(icon);

        // Run the test
        final Icon result = cardManagerUnderTest.downloadImage(0);

        // Verify the results
    }

    /**
     * Test delete card.
     */
    @Test
    public void testDeleteCard() {
        // Setup

        final Card card = new Card();
        card.setId(0);
        card.setTitle("title");
        card.setDescription("description");
        card.setUrl_id(45);
        card.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        card.setCreated_by("created_by");
        card.setTribe("tribe");
        card.setTeam("team");
        // Run the test
        cardManagerUnderTest.deleteCard(card, "email");

        // Verify the results
        verify(mockCardRepository).deleteById(0);
    }

    /**
     * Test get all card.
     */
    @Test
    public void testGetAllCard() {
        // Setup

        // Configure CardRepository.findAll(...).
        final Card card = new Card();
        card.setId(0);
        card.setTitle("title");
        card.setDescription("description");
        card.setUrl_id(45);
        card.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        card.setCreated_by("user@gmail.com");
        card.setTribe("tribe");
        card.setTeam("team");
        final List<Card> cards = Arrays.asList(card);
        when(mockCardRepository.findAll()).thenReturn(cards);

        // Run the test
        final List<CardResponse> result = cardManagerUnderTest.getAllCard("emailId");

        // Verify the results
    }
}
