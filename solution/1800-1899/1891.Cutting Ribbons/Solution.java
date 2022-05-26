class Solution {
    public int maxLength(int[] ribbons, int k) {
        int low = 0, high = 100000;
        while (low < high) {
            int mid = (low + high + 1) >> 1;
            int cnt = 0;
            for (int ribbon : ribbons) {
                cnt += ribbon / mid;
            }
            if (cnt < k) {
                high = mid - 1;
            } else {
                low = mid;
            }
        }
        return low;
    }
}