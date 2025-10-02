package algorithms;
import metrics.PerformanceTracker;
import metrics.CountedIntArray;

public class Kadanes_algorithm {

    public static final class Result {
        public final long maxSum;
        public final int start;
        public final int end;

        public Result(long maxSum, int start, int end) {
            this.maxSum = maxSum;
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Result{maxSum=" + maxSum + ", start=" + start + ", end=" + end + "}";
        }
    }

    public static Result kadane(int[] arr, PerformanceTracker tracker) {
        if (arr == null) throw new IllegalArgumentException("arr == null");
        if (arr.length == 0) throw new IllegalArgumentException("empty array");

        CountedIntArray a = new CountedIntArray(arr, tracker);

        long maxEndingHere = a.get(0);
        long maxSoFar = maxEndingHere;
        int start = 0, end = 0, s = 0;
        tracker.incAssignments();
        tracker.incAssignments();

        for (int i = 1; i < a.length(); i++) {
            int v = a.get(i);

            tracker.incComparisons();
            if (maxEndingHere + v < v) {
                maxEndingHere = v;
                s = i;
                tracker.incAssignments();
            } else {
                maxEndingHere = maxEndingHere + v;
                tracker.incAssignments();
            }

            tracker.incComparisons();
            if (maxEndingHere > maxSoFar) {
                maxSoFar = maxEndingHere;
                start = s;
                end = i;
                tracker.incAssignments();
            }
        }
        return new Result(maxSoFar, start, end);
    }
}
