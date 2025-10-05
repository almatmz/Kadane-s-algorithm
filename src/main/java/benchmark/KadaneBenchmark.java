package benchmark;

import algorithms.Kadane;
import metrics.PerformanceTracker;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class KadaneBenchmark {

    @Param({"100", "1000", "10000", "100000"})
    private int size;

    @Param({"random", "sorted", "reverse", "nearly-sorted"})
    private String dist;

    private int[] arr;

    @Setup(Level.Iteration)
    public void setup() {
        arr = generate(size, dist);
    }

    @Benchmark
    public Kadane.Result runKadane() {
        PerformanceTracker tracker = new PerformanceTracker();
        return Kadane.kadane(arr, tracker);
    }

    private int[] generate(int n, String dist) {
        Random rnd = new Random(42);
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
                int swaps = Math.max(1, n / 100);
                for (int k = 0; k < swaps; k++) {
                    int i = rnd.nextInt(n);
                    int j = rnd.nextInt(n);
                    int tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
                break;

            default:
                Arrays.fill(a, 0);
        }

        return a;
    }
}
