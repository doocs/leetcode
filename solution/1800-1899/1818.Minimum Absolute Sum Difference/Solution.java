class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] diff = new int[n];
        int s = 0;
        for (int i = 0; i < n; ++i) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            s = (s + diff[i]) % MOD;
        }
        if (s == 0) {
            return 0;
        }
        Arrays.sort(nums1);
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            int d = diff[i];
            if (d == 0) {
                continue;
            }
            int b = nums2[i];
            int idx = search(nums1, b);
            int a1 = 1000000, a2 = 1000000;
            if (idx != n) {
                a1 = nums1[idx];
            }
            if (idx != 0) {
                a2 = nums1[idx - 1];
            }
            int c = Math.min(Math.abs(b - a1), Math.abs(b - a2));
            mx = Math.max(mx, d - c);
        }
        return (s - mx + MOD) % MOD;
    }

    private int search(int[] nums, int x) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}