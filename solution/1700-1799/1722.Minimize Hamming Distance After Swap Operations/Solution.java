class Solution {
    private int[] p;

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (int[] e : allowedSwaps) {
            p[find(e[0])] = find(e[1]);
        }
        Map<Integer, Map<Integer, Integer>> mp = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int root = find(i);
            mp.computeIfAbsent(root, k -> new HashMap<>()).put(source[i], mp.get(root).getOrDefault(source[i], 0) + 1);
        }
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int root = find(i);
            if (mp.get(root).getOrDefault(target[i], 0) > 0) {
                mp.get(root).put(target[i], mp.get(root).get(target[i]) - 1);
            } else {
                ++res;
            }
        }
        return res;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}