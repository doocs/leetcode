class Solution {
    private int[] p;

    public boolean possibleBipartition(int n, int[][] dislikes) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        Map<Integer, List<Integer>> mp = new HashMap<>();
        for (int[] e : dislikes) {
            mp.computeIfAbsent(e[0] - 1, k -> new ArrayList<>()).add(e[1] - 1);
            mp.computeIfAbsent(e[1] - 1, k -> new ArrayList<>()).add(e[0] - 1);
        }
        for (int i = 0; i < n; ++i) {
            List<Integer> dis = mp.getOrDefault(i, new ArrayList<>());
            for (int j : dis) {
                if (find(i) == find(j)) {
                    return false;
                }
                p[find(j)] = find(dis.get(0));
            }
        }
        return true;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}