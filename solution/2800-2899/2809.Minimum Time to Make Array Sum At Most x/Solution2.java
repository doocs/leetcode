class Solution {
    public int minimumTime(List<Integer> nums1, List<Integer> nums2, int x) {
        int n = nums1.size();
        int[] f = new int[n + 1];
        int[][] nums = new int[n][0];
        for (int i = 0; i < n; ++i) {
            nums[i] = new int[] {nums1.get(i), nums2.get(i)};
        }
        Arrays.sort(nums, Comparator.comparingInt(a -> a[1]));
        for (int[] e : nums) {
            int a = e[0], b = e[1];
            for (int j = n; j > 0; --j) {
                f[j] = Math.max(f[j], f[j - 1] + a + b * j);
            }
        }
        int s1 = 0, s2 = 0;
        for (int v : nums1) {
            s1 += v;
        }
        for (int v : nums2) {
            s2 += v;
        }

        for (int j = 0; j <= n; ++j) {
            if (s1 + s2 * j - f[j] <= x) {
                return j;
            }
        }
        return -1;
    }
}