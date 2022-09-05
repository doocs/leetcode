class RLEIterator {
    private int[] encoding;
    private int curr;
    private int i;

    public RLEIterator(int[] encoding) {
        this.encoding = encoding;
        curr = 0;
        i = 0;
    }

    public int next(int n) {
        while (i < encoding.length) {
            if (curr + n > encoding[i]) {
                n -= encoding[i] - curr;
                i += 2;
                curr = 0;
            } else {
                curr += n;
                return encoding[i + 1];
            }
        }
        return -1;
    }
}

/**
 * Your RLEIterator object will be instantiated and called as such:
 * RLEIterator obj = new RLEIterator(encoding);
 * int param_1 = obj.next(n);
 */