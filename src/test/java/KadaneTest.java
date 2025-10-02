import algorithms.Kadane;
import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KadaneTest {

    @Test
    public void testNullArrayThrows() {
        assertThrows(IllegalArgumentException.class, () -> Kadane.kadane(null, new PerformanceTracker()));
    }

    @Test
    public void testEmptyArrayThrows() {
        assertThrows(IllegalArgumentException.class, () -> Kadane.kadane(new int[0], new PerformanceTracker()));
    }

    @Test
    public void singleElement() {
        PerformanceTracker t = new PerformanceTracker();
        Kadane.Result r = Kadane.kadane(new int[]{42}, t);
        assertEquals(42, r.maxSum);
        assertEquals(0, r.start);
        assertEquals(0, r.end);
    }

    @Test
    public void allNegative() {
        PerformanceTracker t = new PerformanceTracker();
        int[] arr = new int[]{-5, -1, -8, -3};
        Kadane.Result r = Kadane.kadane(arr, t);
        assertEquals(-1, r.maxSum);
        assertEquals(1, r.start);
        assertEquals(1, r.end);
    }

    @Test
    public void mixedArray() {
        PerformanceTracker t = new PerformanceTracker();
        int[] arr = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        Kadane.Result r = Kadane.kadane(arr, t);
        assertEquals(6, r.maxSum);
        assertEquals(3, r.start);
        assertEquals(6, r.end);
    }

    @Test
    public void duplicatesAndZeros() {
        PerformanceTracker t = new PerformanceTracker();
        int[] arr = new int[]{0, 0, 0, 0};
        Kadane.Result r = Kadane.kadane(arr, t);
        assertEquals(0, r.maxSum);
        assertTrue(r.start >= 0 && r.end >= r.start);
    }
}
