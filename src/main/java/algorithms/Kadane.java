package algorithms;

import metrics.PerformanceTracker;

public final class Kadane {

    public static class Result {
        public final int maxSum;
        public final int start;
        public final int end;

        public Result(int maxSum, int start, int end) {
            this.maxSum = maxSum;
            this.start = start;
            this.end = end;
        }
    }

    public static Result kadane(int[] arr, PerformanceTracker tracker) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Input array must not be null or empty");
        }

        if (arr == null || arr.length == 0) {
            return new Result(0, -1, -1);
        }
        if (arr.length == 1) {
            return new Result(arr[0], 0, 0);
        }

        int maxSoFar = arr[0];
        int maxEndingHere = arr[0];
        int start = 0, tempStart = 0, end = 0;

        if (tracker != null) {
            tracker.incArrayAccesses();
        }

        for (int i = 1; i < arr.length; i++) {
            if (tracker != null) tracker.incArrayAccesses();

            if (maxEndingHere + arr[i] < arr[i]) {
                maxEndingHere = arr[i];
                tempStart = i;
            } else {
                maxEndingHere += arr[i];
            }

            if (tracker != null) tracker.incComparisons();

            if (maxEndingHere > maxSoFar) {
                maxSoFar = maxEndingHere;
                start = tempStart;
                end = i;
            }
        }

        return new Result(maxSoFar, start, end);
    }
}
