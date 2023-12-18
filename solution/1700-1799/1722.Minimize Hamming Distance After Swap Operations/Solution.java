class Solution {
    private int[] p;

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
        for (int[] a : allowedSwaps) {
            p[find(a[0])] = find(a[1]);
        }
        Map<Integer, Map<Integer, Integer>> cnt = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int j = find(i);
            cnt.computeIfAbsent(j, k -> new HashMap<>()).merge(source[i], 1, Integer::sum);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int j = find(i);
            Map<Integer, Integer> t = cnt.get(j);
            if (t.merge(target[i], -1, Integer::sum) < 0) {
                ++ans;
            }
        }
        return ans;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}