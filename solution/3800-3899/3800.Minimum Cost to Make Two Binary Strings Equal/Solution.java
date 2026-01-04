class Solution {
    public long minimumCost(String s, String t, int flipCost, int swapCost, int crossCost) {
        long[] diff = new long[2];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c1 = s.charAt(i), c2 = t.charAt(i);
            if (c1 != c2) {
                diff[c1 - '0']++;
            }
        }

        long ans = (diff[0] + diff[1]) * flipCost;

        long mx = Math.max(diff[0], diff[1]);
        long mn = Math.min(diff[0], diff[1]);
        ans = Math.min(ans, mn * swapCost + (mx - mn) * flipCost);

        long avg = (mx + mn) / 2;
        ans = Math.min(
            ans, (avg - mn) * crossCost + avg * swapCost + (mx + mn - avg * 2) * flipCost);
        return ans;
    }
}
