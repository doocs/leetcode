class Solution {
    private int[] presum;

    public Solution(int[] w) {
        int n = w.length;
        presum = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            presum[i + 1] = presum[i] + w[i];
        }
    }
    
    public int pickIndex() {
        int n = presum.length;
        int x = (int) (Math.random() * presum[n - 1]) + 1;
        int left = 0, right = n - 2;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (presum[mid + 1] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */