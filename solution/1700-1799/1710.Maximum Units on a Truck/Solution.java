class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int ans = 0;
        for (var v : boxTypes) {
            int a = Math.min(v[0], truckSize);
            truckSize -= a;
            ans += a * v[1];
            if (truckSize == 0) {
                break;
            }
        }
        return ans;
    }
}