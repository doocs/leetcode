class Solution {
    private int n;

    public int minOperations(int[] nums1, int[] nums2) {
        n = nums1.length;
        int a = f(nums1, nums2, nums1[n - 1], nums2[n - 1]);
        int b = f(nums1, nums2, nums2[n - 1], nums1[n - 1]);
        return a + b == -2 ? -1 : Math.min(a, b + 1);
    }

    private int f(int[] nums1, int[] nums2, int x, int y) {
        int cnt = 0;
        for (int i = 0; i < n - 1; ++i) {
            if (nums1[i] <= x && nums2[i] <= y) {
                continue;
            }
            if (!(nums1[i] <= y && nums2[i] <= x)) {
                return -1;
            }
            ++cnt;
        }
        return cnt;
    }
}