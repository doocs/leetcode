class CustomStack {
    private int[] s;
    private int t;

    public CustomStack(int maxSize) {
        s = new int[maxSize];
    }
    
    public void push(int x) {
        if (t < s.length) {
            s[t++] = x;
        }
    }
    
    public int pop() {
        return t == 0 ? -1 : s[--t];
    }
    
    public void increment(int k, int val) {
        for (int i = 0; i < Math.min(k, t); ++i) {
            s[i] += val;
        }
    }
}

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack obj = new CustomStack(maxSize);
 * obj.push(x);
 * int param_2 = obj.pop();
 * obj.increment(k,val);
 */