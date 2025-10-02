package metrics;

public class CountedIntArray {
    private final int[] arr;
    private final PerformanceTracker tracker;

    public CountedIntArray(int[] arr, PerformanceTracker tracker) {
        if (arr == null) throw new IllegalArgumentException("arr == null");
        if (tracker == null) throw new IllegalArgumentException("tracker == null");
        this.arr = arr;
        this.tracker = tracker;
    }

    public int get(int i) {
        tracker.incArrayAccesses();
        return arr[i];
    }

    public int length() {
        return arr.length;
    }
}
