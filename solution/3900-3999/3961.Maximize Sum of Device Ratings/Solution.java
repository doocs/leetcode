class Solution {
    public long maxRatings(int[][] units) {
        int n = units[0].length;
        if (n == 1) {
            long ans = 0;
            for (int[] x : units) {
                ans += x[0];
            }
            return ans;
        }

        long ans = 0;
        int mn = Integer.MAX_VALUE;
        int mn2 = Integer.MAX_VALUE;

        for (int[] x : units) {
            Arrays.sort(x);
            ans += x[1];
            mn2 = Math.min(mn2, x[1]);
            mn = Math.min(mn, x[0]);
        }

        ans -= (mn2 - mn);

        return ans;
    }
}