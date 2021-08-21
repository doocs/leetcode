class MovingAverage {
    private int size;
    private int[] data;
    private int sum;
    private int count;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.size = size;
        this.data = new int[size];
    }
    
    public double next(int val) {
        int idx = count % size;
        int oldVal = data[idx];
        data[idx] = val;
        sum += val - oldVal;
        ++count;
        return sum * 1.0 / Math.min(count, size);
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */