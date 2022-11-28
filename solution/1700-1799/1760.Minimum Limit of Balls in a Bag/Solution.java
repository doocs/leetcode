class Solution {
    public int minimumSize(int[] nums, int maxOperations) {
        int left = 1, right = (int) 1e9;
        while (left < right) {
            int mid = (left + right) >>> 1;
            long s = 0;
            for (int v : nums) {
                s += (v - 1) / mid;
            }
            if (s <= maxOperations) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}