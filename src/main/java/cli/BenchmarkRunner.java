package cli;

import algorithms.Kadane;
import metrics.PerformanceTracker;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class BenchmarkRunner {

    private static int[] SIZES = new int[]{100, 1000, 10000, 100000};
    private static int RUNS = 5;
    private static String[] DISTS = new String[]{"random", "sorted", "reverse", "nearly-sorted"};

    public static void main(String[] args) throws Exception {
        try (PrintWriter out = new PrintWriter(new FileWriter("benchmark_kadane.csv"))) {
            out.println("size,dist,run,elapsed_ms,comparisons,array_accesses,assignments,max_sum,start,end");
            for (int size : SIZES) {
                for (String dist : DISTS) {
                    for (int run = 0; run < RUNS; run++) {
                        int[] arr = generate(size, dist, run);
                        PerformanceTracker tracker = new PerformanceTracker();
                        tracker.startTimer();
                        Kadane.Result r = Kadane.kadane(arr, tracker);
                        tracker.stopTimer();
                        out.printf("%d,%s,%d,%.3f,%d,%d,%d,%d,%d,%d%n",
                                size, dist, run,
                                tracker.getElapsedMillis(),
                                tracker.getComparisons(),
                                tracker.getArrayAccesses(),
                                tracker.getAssignments(),
                                r.maxSum, r.start, r.end);
                        out.flush();
                        Thread.sleep(10);
                    }
                }
            }
        }
        System.out.println("Done. CSV written: benchmark_kadane.csv");
    }

    private static int[] generate(int n, String dist, int seed) {
        Random rnd = new Random(31L * seed + n);
        int[] a = new int[n];
        switch (dist) {
            case "random":
                for (int i = 0; i < n; i++) a[i] = rnd.nextInt(201) - 100;
                break;
            case "sorted":
                for (int i = 0; i < n; i++) a[i] = i % 100;
                break;
            case "reverse":
                for (int i = 0; i < n; i++) a[i] = (n - i) % 100;
                break;
            case "nearly-sorted":
                for (int i = 0; i < n; i++) a[i] = i % 50;
                for (int k = 0; k < Math.max(1, n / 100); k++) {
                    int i = rnd.nextInt(n), j = rnd.nextInt(n);
                    int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
                }
                break;
            default:
                Arrays.fill(a, 0);
        }
        return a;
    }
}
