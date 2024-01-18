class Solution {
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        final int mod = (int) 1e9 + 7;
        int[] nums = nums1.clone();
        Arrays.sort(nums);
        int s = 0, n = nums.length;
        for (int i = 0; i < n; ++i) {
            s = (s + Math.abs(nums1[i] - nums2[i])) % mod;
        }
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            int d1 = Math.abs(nums1[i] - nums2[i]);
            int d2 = 1 << 30;
            int j = search(nums, nums2[i]);
            if (j < n) {
                d2 = Math.min(d2, Math.abs(nums[j] - nums2[i]));
            }
            if (j > 0) {
                d2 = Math.min(d2, Math.abs(nums[j - 1] - nums2[i]));
            }
            mx = Math.max(mx, d1 - d2);
        }
        return (s - mx + mod) % mod;
    }

    private int search(int[] nums, int x) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}