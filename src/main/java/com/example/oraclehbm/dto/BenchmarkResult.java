package com.example.oraclehbm.dto;

import java.util.List;

/**
 * Generic wrapper for benchmark results including timing information.
 */
public class BenchmarkResult<T> {

    private List<T> data;
    private int count;
    private long elapsedMillis;

    public BenchmarkResult() {
    }

    public BenchmarkResult(List<T> data, long elapsedMillis) {
        this.data = data;
        this.count = data != null ? data.size() : 0;
        this.elapsedMillis = elapsedMillis;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
        this.count = data != null ? data.size() : 0;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getElapsedMillis() {
        return elapsedMillis;
    }

    public void setElapsedMillis(long elapsedMillis) {
        this.elapsedMillis = elapsedMillis;
    }
}
