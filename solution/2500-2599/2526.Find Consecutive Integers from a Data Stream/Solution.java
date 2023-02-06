class DataStream {
    private int cnt;
    private int val;
    private int k;

    public DataStream(int value, int k) {
        val = value;
        this.k = k;
    }

    public boolean consec(int num) {
        cnt = num == val ? cnt + 1 : 0;
        return cnt >= k;
    }
}

/**
 * Your DataStream object will be instantiated and called as such:
 * DataStream obj = new DataStream(value, k);
 * boolean param_1 = obj.consec(num);
 */