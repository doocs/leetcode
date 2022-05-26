class Solution {
    public int minimumSize(int[] nums, int maxOperations) {
        int left = 1, right = (int) 1e9;
        while (left < right) {
            int mid = (left + right) >>> 1;
            long ops = 0;
            for (int num : nums) {
                ops += (num - 1) / mid;
            }
            if (ops <= maxOperations) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}