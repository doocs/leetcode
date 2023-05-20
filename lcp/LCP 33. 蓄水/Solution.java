class Solution {
    public int storeWater(int[] bucket, int[] vat) {
        int mx = Arrays.stream(vat).max().getAsInt();
        if (mx == 0) {
            return 0;
        }
        int n = vat.length;
        int ans = 1 << 30;
        for (int x = 1; x <= mx; ++x) {
            int y = 0;
            for (int i = 0; i < n; ++i) {
                y += Math.max(0, (vat[i] + x - 1) / x - bucket[i]);
            }
            ans = Math.min(ans, x + y);
        }
        return ans;
    }
}