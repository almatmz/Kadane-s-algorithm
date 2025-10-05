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

    public void set(int i, int value) {
        tracker.incArrayAccesses();
        tracker.incAssignments();
        arr[i] = value;
    }

    public int length() {
        return arr.length;
    }

    public int[] getRawArray() {
        return arr;
    }
}
