class Solution {
    public int countSubarrays(int[] nums, int k) {
        int n = nums.length;
        int i = 0;
        for (int j = 0; j < n; ++j) {
            if (nums[j] == k) {
                i = j;
                break;
            }
        }
        int ans = 1;
        int[] d = new int[n << 1 | 1];
        int mi = 0, mx = 0;
        for (int j = i + 1; j < n; ++j) {
            if (nums[j] < k) {
                ++mi;
            } else {
                ++mx;
            }
            if (mx - mi >= 0 && mx - mi <= 1) {
                ++ans;
            }
            ++d[mx - mi + n];
        }
        mi = 0;
        mx = 0;
        for (int j = i - 1; j >= 0; --j) {
            if (nums[j] < k) {
                ++mi;
            } else {
                ++mx;
            }
            if (mx - mi >= 0 && mx - mi <= 1) {
                ++ans;
            }
            ans += d[mi - mx + n] + d[mi - mx + 1 + n];
        }
        return ans;
    }
}