package org.codingmatters.patterns;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertThat;

public class TestFirstOf {

    @Test
    public void testSimple() throws Exception {
        String result = new FirstOf<String, Exception>(Optional::empty)
                .orElse(Optional::empty)
                .orElse(() -> Optional.of("Dory is the new twelve"))
                .orElse(Optional::empty)
                .get()
                .orElse("Dory");

        assertThat(result, Matchers.is("Dory is the new twelve"));
    }

    @Test
    public void testDefault() throws Exception {
        String result = new FirstOf<String, Exception>(Optional::empty)
                .orElse(Optional::empty)
                .orElse(Optional::empty)
                .get()
                .orElse("Dory");

        assertThat(result, Matchers.is("Dory"));
    }

    @Test(expected = NullPointerException.class)
    public void testException() throws Exception {
        new FirstOf<String, Exception>(Optional::empty)
                .orElse(() -> {throw new NullPointerException("dont't panic");})
                .orElse(() -> Optional.of("Dory is the new twelve"))
                .get()
                .orElse("Dory");
    }
}
