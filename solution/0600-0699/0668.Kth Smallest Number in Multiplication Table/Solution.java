class Solution {
    public int findKthNumber(int m, int n, int k) {
        int left = 1, right = m * n;
        while (left < right) {
            int mid = (left + right) >>> 1;
            int cnt = 0;
            for (int i = 1; i <= m; ++i) {
                cnt += Math.min(mid / i, n);
            }
            if (cnt >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}