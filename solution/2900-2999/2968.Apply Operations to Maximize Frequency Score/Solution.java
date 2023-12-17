class Solution {
    public int maxFrequencyScore(int[] nums, long k) {
        Arrays.sort(nums);
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            s[i] = s[i - 1] + nums[i - 1];
        }
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            boolean ok = false;

            for (int i = 0; i <= n - mid; i++) {
                int j = i + mid;
                int x = nums[(i + j) / 2];
                long left = ((i + j) / 2 - i) * (long) x - (s[(i + j) / 2] - s[i]);
                long right = (s[j] - s[(i + j) / 2]) - ((j - (i + j) / 2) * (long) x);
                if (left + right <= k) {
                    ok = true;
                    break;
                }
            }

            if (ok) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        return l;
    }
}