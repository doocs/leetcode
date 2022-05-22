class Solution {
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int n = capacity.length;
        int[] d = new int[n];
        for (int i = 0; i < n; ++i) {
            d[i] = capacity[i] - rocks[i];
        }
        Arrays.sort(d);
        int ans = 0;
        for (int v : d) {
            if (v <= additionalRocks) {
                ++ans;
                additionalRocks -= v;
            } else {
                break;
            }
        }
        return ans;
    }
}