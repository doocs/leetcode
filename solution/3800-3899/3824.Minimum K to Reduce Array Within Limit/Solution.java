class Solution {
    public int minimumK(int[] nums) {
        int l = 1, r = 100000;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(nums, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean check(int[] nums, int k) {
        long t = 0;
        for (int x : nums) {
            t += (x + k - 1) / k;
        }
        return t <= 1L * k * k;
    }
}
