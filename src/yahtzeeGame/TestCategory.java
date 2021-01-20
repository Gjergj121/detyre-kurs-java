package yahtzeeGame;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestCategory {

    Category category;

    @BeforeEach
    public void setUp() {
        category = new Category(5);
    }

    @Test
    public void assertNjeshat() {
        int[] diceState = {1, 1, 2, 1, 3};
        assertEquals(category.llogaritNjeshat(diceState), 3, "Duhet te jete 3");

        int[] diceState2 = {2, 5, 2, 6, 3};
        assertEquals(category.llogaritNjeshat(diceState2), 0, "Duhet te jete 0");
    }


}
