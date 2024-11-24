class Solution {
    public long shiftDistance(String s, String t, int[] nextCost, int[] previousCost) {
        int m = 26;
        long[] s1 = new long[(m << 1) + 1];
        long[] s2 = new long[(m << 1) + 1];
        for (int i = 0; i < (m << 1); i++) {
            s1[i + 1] = s1[i] + nextCost[i % m];
            s2[i + 1] = s2[i] + previousCost[(i + 1) % m];
        }
        long ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int x = s.charAt(i) - 'a';
            int y = t.charAt(i) - 'a';
            long c1 = s1[y + (y < x ? m : 0)] - s1[x];
            long c2 = s2[x + (x < y ? m : 0)] - s2[y];
            ans += Math.min(c1, c2);
        }
        return ans;
    }
}
