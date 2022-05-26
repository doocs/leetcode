class Solution {
    public int splitArray(int[] nums, int m) {
        int mx = -1;
        for (int num : nums) {
            mx = Math.max(mx, num);
        }
        int left = mx, right = (int) 1e9;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(nums, m, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[] nums, int m, int x) {
        int s = 0, cnt = 1;
        for (int num : nums) {
            if (s + num > x) {
                ++cnt;
                s = num;
            } else {
                s += num;
            }
        }
        return cnt <= m;
    }
}