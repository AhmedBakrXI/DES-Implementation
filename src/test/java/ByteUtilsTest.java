import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ByteUtilsTest {
    @Test
    public void testLeftShift() {
        boolean[] arr = {true, false, false, false, false};
        boolean[] result = ByteUtils.leftShift(arr, 2);
        boolean[] expected = {false, false, false, true, false};

        assertArrayEquals(expected, result);
    }
}
