class Solution {
    private int[] nums;
    private int mn;
    private int mx;
    private static final int INF = Integer.MAX_VALUE / 2;

    public int[] makeParityAlternating(int[] nums) {
        if (nums.length == 1) {
            return new int[] {0, 0};
        }
        this.nums = nums;

        mn = INF;
        mx = -INF;
        for (int x : nums) {
            mn = Math.min(mn, x);
            mx = Math.max(mx, x);
        }

        int[] r0 = f(0);
        int[] r1 = f(1);

        if (r0[0] != r1[0]) {
            return r0[0] < r1[0] ? r0 : r1;
        }
        return r0[1] <= r1[1] ? r0 : r1;
    }

    private int[] f(int k) {
        int cnt = 0;
        int a = INF;
        int b = -INF;

        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            if (((x - i) & 1) != k) {
                cnt++;
                if (x == mn) {
                    x += 1;
                } else if (x == mx) {
                    x -= 1;
                }
            }
            a = Math.min(a, x);
            b = Math.max(b, x);
        }
        return new int[] {cnt, Math.max(1, b - a)};
    }
}
