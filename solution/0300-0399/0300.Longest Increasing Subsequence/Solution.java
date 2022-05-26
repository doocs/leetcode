class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] d = new int[n + 1];
        d[1] = nums[0];
        int size = 1;
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[size]) {
                d[++size] = nums[i];
            } else {
                int left = 1, right = size;
                while (left < right) {
                    int mid = (left + right) >> 1;
                    if (d[mid] >= nums[i]) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                int p = d[left] >= nums[i] ? left : 1;
                d[p] = nums[i];
            }
        }
        return size;
    }
}