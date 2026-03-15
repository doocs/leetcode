class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        int[] prefixGcd = new int[n];
        int mx = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            mx = Math.max(mx, x);
            prefixGcd[i] = gcd(x, mx);
        }

        Arrays.sort(prefixGcd);

        long ans = 0;
        for (int i = 0; i < n / 2; i++) {
            ans += gcd(prefixGcd[i], prefixGcd[n - i - 1]);
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
