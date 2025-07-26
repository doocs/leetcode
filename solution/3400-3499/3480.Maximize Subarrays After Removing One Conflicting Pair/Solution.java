class Solution {
    public long maxSubarrays(int n, int[][] conflictingPairs) {
        List<Integer>[] g = new List[n + 1];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] pair : conflictingPairs) {
            int a = pair[0], b = pair[1];
            if (a > b) {
                int c = a;
                a = b;
                b = c;
            }
            g[a].add(b);
        }
        long[] cnt = new long[n + 2];
        long ans = 0, add = 0;
        int b1 = n + 1, b2 = n + 1;
        for (int a = n; a > 0; --a) {
            for (int b : g[a]) {
                if (b < b1) {
                    b2 = b1;
                    b1 = b;
                } else if (b < b2) {
                    b2 = b;
                }
            }
            ans += b1 - a;
            cnt[b1] += b2 - b1;
            add = Math.max(add, cnt[b1]);
        }
        ans += add;
        return ans;
    }
}
