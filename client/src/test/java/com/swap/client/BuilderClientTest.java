package com.swap.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuilderClientTest {

    @Test
    void create() {
        var cle = BuilderClient.create("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");
        var r =cle.getContributor("google","cel-go");
        assertNotNull(r);
    }



}