package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    @Test
    void greetingReturnsExpectedMessage() {
        assertEquals(
            "Hello World from Maven and Jenkins!",
            App.greeting()
        );
    }
}
