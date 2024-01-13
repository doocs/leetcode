class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        int[] cnt = new int[1001];
        for (var e : boxTypes) {
            int a = e[0], b = e[1];
            cnt[b] += a;
        }
        int ans = 0;
        for (int b = 1000; b > 0 && truckSize > 0; --b) {
            int a = cnt[b];
            if (a > 0) {
                ans += b * Math.min(truckSize, a);
                truckSize -= a;
            }
        }
        return ans;
    }
}