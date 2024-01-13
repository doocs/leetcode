class RecentCounter {
    private int[] s = new int[10010];
    private int idx;

    public RecentCounter() {
    }

    public int ping(int t) {
        s[idx++] = t;
        return idx - search(t - 3000);
    }

    private int search(int x) {
        int left = 0, right = idx;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (s[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */