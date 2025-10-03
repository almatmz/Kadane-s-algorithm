package benchmark;

import algorithms.Kadane;
import metrics.PerformanceTracker;
import org.openjdk.jmh.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class KadaneBenchmark {

    @Param({"100", "1000", "10000", "100000"})
    private int size;

    private int[] arr;

    @Setup(Level.Iteration)
    public void setup() {
        Random rnd = new Random(42);
        arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rnd.nextInt(201) - 100;
        }
    }

    @Benchmark
    public Kadane.Result runKadane() {
        PerformanceTracker tracker = new PerformanceTracker();
        return Kadane.kadane(arr, tracker);
    }
}
