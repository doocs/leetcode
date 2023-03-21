class Solution {
    public int maxLength(int[] ribbons, int k) {
        int left = 0, right = 100000;
        while (left < right) {
            int mid = (left + right + 1) >>> 1;
            int cnt = 0;
            for (int x : ribbons) {
                cnt += x / mid;
            }
            if (cnt >= k) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}