class Solution {
    public int maxValidSplits(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int del = -1; del < n; ++del) {
            int m = del == -1 ? n : n - 1;
            int[] arr = new int[m];
            for (int i = 0, j = 0; i < n; ++i) {
                if (i != del) {
                    arr[j++] = nums[i];
                }
            }
            ans = Math.max(ans, calc(arr));
        }
        return ans;
    }

    private int calc(int[] arr) {
        int m = arr.length;
        int[] pre = new int[m];
        int[] suf = new int[m];
        pre[0] = arr[0];
        for (int i = 1; i < m; ++i) {
            pre[i] = gcd(pre[i - 1], arr[i]);
        }
        suf[m - 1] = arr[m - 1];
        for (int i = m - 2; i >= 0; --i) {
            suf[i] = gcd(suf[i + 1], arr[i]);
        }
        int ans = 0;
        for (int i = 0; i < m - 1; ++i) {
            if (pre[i] == suf[i + 1]) {
                ++ans;
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
