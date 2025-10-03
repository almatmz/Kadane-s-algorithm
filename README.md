# Kadane’s Algorithm — Maximum Subarray Sum

## 📌 Overview
This project implements **Kadane’s Algorithm** to find the maximum subarray sum in a given array.  
This is **Assignment 2** (Pair 3, Linear Array Algorithms).

- **Elkham (Partner)** → Boyer–Moore Majority Vote Algorithm
- **Almat (Me)** → Kadane’s Algorithm

The implementation includes:
- Clean, documented Java code
- Edge-case handling (empty and single-element arrays)
- Performance tracking (comparisons, array accesses, time)
- CLI for configurable benchmarking
- JMH microbenchmarks for precise performance measurement
- Unit tests for correctness

---

Run Unit Tests
mvn test

Run CLI Benchmark:

   java -cp target/classes cli.BenchmarkRunner


This writes results into benchmark_kadane.csv.

Run JMH Microbenchmark:

java -jar target/Kadane_algorithm-1.0-SNAPSHOT.jar