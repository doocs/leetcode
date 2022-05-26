class Solution {
    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        int n = values.length;
        int[][] p = new int[n][2];
        for (int i = 0; i < n; ++i) {
            p[i] = new int[]{values[i], labels[i]};
        }
        Arrays.sort(p, (a, b) -> b[0] - a[0]);
        int ans = 0;
        int num = 0;
        Map<Integer, Integer> counter = new HashMap<>();
        for (int i = 0; i < n && num < numWanted; ++i) {
            int v = p[i][0], l = p[i][1];
            if (counter.getOrDefault(l, 0) < useLimit) {
                counter.put(l, counter.getOrDefault(l, 0) + 1);
                ans += v;
                ++num;
            }
        }
        return ans;
    }
}