package metrics;

public class PerformanceTracker {
    private long comparisons = 0;
    private long arrayAccesses = 0;
    private long assignments = 0;
    private long startNano = 0;
    private long elapsedNano = 0;

    public void incComparisons() {
        comparisons++;
    }

    public void incArrayAccesses() {
        arrayAccesses++;
    }
    public void incAssignments() {
        assignments++;
    }

    public long getComparisons() {
        return comparisons;
    }
    public long getArrayAccesses() {
        return arrayAccesses;
    }
    public long getAssignments() {
        return assignments;
    }

    public void startTimer() {
        startNano = System.nanoTime();
    }
    public void stopTimer() {
        elapsedNano = System.nanoTime() - startNano;
    }
    public double getElapsedMillis() {
        return elapsedNano / 1_000_000.0;
    }

    public String toCsvRowPrefix() {
        return comparisons + "," + arrayAccesses + "," + assignments + "," + getElapsedMillis();
    }
}
