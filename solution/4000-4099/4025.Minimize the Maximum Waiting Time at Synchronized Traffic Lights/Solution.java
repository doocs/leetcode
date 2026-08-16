class Solution {
    public int minPenalty(int period, int[] lights, int[] arrivalTime) {
        int mx = 0;
        for (int x : lights) {
            mx = Math.max(mx, x);
        }

        int ans = 0;

        for (int x : arrivalTime) {
            int r = x % period;

            if (r >= mx) {
                ans = Math.max(ans, period - r);
            }
        }

        return ans;
    }
}