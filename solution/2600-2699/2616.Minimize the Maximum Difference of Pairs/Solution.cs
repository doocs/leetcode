public class Solution {
    public int MinimizeMax(int[] nums, int p) {
        Array.Sort(nums);
        int n = nums.Length;
        int l = 0, r = nums[n - 1] - nums[0] + 1;

        bool check(int diff) {
            int cnt = 0;
            for (int i = 0; i < n - 1; ++i) {
                if (nums[i + 1] - nums[i] <= diff) {
                    ++cnt;
                    ++i;
                }
            }
            return cnt >= p;
        }

        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }
}
