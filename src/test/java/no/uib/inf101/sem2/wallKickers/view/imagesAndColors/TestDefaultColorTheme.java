package no.uib.inf101.sem2.wallKickers.view.imagesAndColors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.awt.Color;
import org.junit.jupiter.api.Test;

public class TestDefaultColorTheme {
    
    @Test
    public void sanityTestDefaultColorTheme() {
        ColorTheme colors = new DefaultColorTheme();
        assertEquals(Color.WHITE, colors.getFrameColor());
        assertEquals(new Color(0, 0, 0, 0), colors.getCellColor('-'));
        assertEquals(Color.LIGHT_GRAY, colors.getCellColor('W'));
        assertThrows(IllegalArgumentException.class, () -> colors.getCellColor('\n'));
    }
}
