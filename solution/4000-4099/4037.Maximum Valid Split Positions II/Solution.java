class Solution {
    public int maxValidSplits(int[] nums) {
        int n = nums.length;

        boolean[] pos1 = mark(nums);

        int[] rev = nums.clone();
        for (int i = 0; i < n / 2; ++i) {
            int t = rev[i];
            rev[i] = rev[n - 1 - i];
            rev[n - 1 - i] = t;
        }

        boolean[] pos2 = mark(rev);

        int ans = calc(nums);

        for (int i = 0; i < n; ++i) {
            if (pos1[i] || pos2[n - 1 - i]) {
                int[] arr = new int[n - 1];
                for (int j = 0, k = 0; j < n; ++j) {
                    if (j != i) {
                        arr[k++] = nums[j];
                    }
                }
                ans = Math.max(ans, calc(arr));
            }
        }

        return ans;
    }

    private boolean[] mark(int[] nums) {
        int n = nums.length;
        boolean[] pos = new boolean[n];

        pos[0] = true;
        int g = nums[0];

        for (int i = 1; i < n; ++i) {
            int ng = gcd(g, nums[i]);
            pos[i] = ng != g;
            g = ng;
        }

        return pos;
    }

    private int calc(int[] arr) {
        int n = arr.length;
        int[] pre = new int[n];
        int[] suf = new int[n];

        pre[0] = arr[0];
        for (int i = 1; i < n; ++i) {
            pre[i] = gcd(pre[i - 1], arr[i]);
        }

        suf[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            suf[i] = gcd(suf[i + 1], arr[i]);
        }

        int ans = 0;
        for (int i = 0; i + 1 < n; ++i) {
            if (pre[i] == suf[i + 1]) {
                ++ans;
            }
        }

        return ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}
