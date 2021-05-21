class CustomStack {
    private int[] s;
    private int tail;

    public CustomStack(int maxSize) {
        s = new int[maxSize];
    }
    
    public void push(int x) {
        if (tail < s.length) {
            s[tail++] = x;
        }
    }
    
    public int pop() {
        return tail == 0 ? -1 : s[--tail];
    }
    
    public void increment(int k, int val) {
        for (int i = 0; i < Math.min(k, tail); ++i) {
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