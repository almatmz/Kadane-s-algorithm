package metrics;

import java.io.FileWriter;
import java.io.IOException;

public class PerformanceTracker {

    private long comparisons = 0;
    private long arrayAccesses = 0;
    private long assignments = 0;
    private long swaps = 0;
    private long allocations = 0;

    private long startNs = 0;
    private long endNs = 0;
    private long startUsedBytes = 0;
    private long endUsedBytes = 0;

    public void startTimer() {
        startNs = System.nanoTime();
        Runtime rt = Runtime.getRuntime();
        startUsedBytes = rt.totalMemory() - rt.freeMemory();
    }

    public void stopTimer() {
        endNs = System.nanoTime();
        Runtime rt = Runtime.getRuntime();
        endUsedBytes = rt.totalMemory() - rt.freeMemory();
    }


    public double getElapsedMillis() {
        return (endNs - startNs) / 1_000_000.0;
    }



    public void incComparisons() { comparisons++; }
    public void incArrayAccesses() { arrayAccesses++; }


    public long getComparisons() { return comparisons; }
    public long getArrayAccesses() { return arrayAccesses; }


    public void reset() {
        comparisons = 0;
        arrayAccesses = 0;
        assignments = 0;
        swaps = 0;
        allocations = 0;
        startNs = 0;
        endNs = 0;
        startUsedBytes = 0;
        endUsedBytes = 0;
    }


}
