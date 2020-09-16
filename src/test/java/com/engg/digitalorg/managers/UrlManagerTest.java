package com.engg.digitalorg.managers;

import com.engg.digitalorg.model.entity.Url;
import com.engg.digitalorg.repository.UrlRepository;
import com.engg.digitalorg.util.BaseConversion;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;

public class UrlManagerTest {

    @Mock
    private UrlRepository mockUrlRepository;
    @Mock
    private BaseConversion mockBaseConversion;

    @InjectMocks
    private UrlManager urlManagerUnderTest;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testGetOriginalUrl() {
        // Setup
        when(mockBaseConversion.decode("input")).thenReturn(0L);

        // Configure UrlRepository.findById(...).
        final Url url1 = new Url();
        url1.setId(0);
        url1.setLong_url("long_url");
        url1.setCreated_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        url1.setCard_id(0);
        url1.setShort_url("short_url");
        url1.setExpires_date(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        final Optional<Url> url = Optional.of(url1);
        when(mockUrlRepository.findById(0L)).thenReturn(url);

        // Run the test
        final String result = urlManagerUnderTest.getOriginalUrl("shortUrl");

        // Verify the results
        assertEquals("result", result);
        verify(mockUrlRepository).delete(any(Url.class));
    }
}
