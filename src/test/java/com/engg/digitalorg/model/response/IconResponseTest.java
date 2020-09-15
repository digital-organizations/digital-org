package com.engg.digitalorg.model.response;

import org.testng.annotations.BeforeMethod;

public class IconResponseTest {

    private IconResponse iconResponseUnderTest;

    @BeforeMethod
    public void setUp() {
        iconResponseUnderTest = new IconResponse("originalFilename", "contentType", "content".getBytes(), 0, 0);
    }
}
