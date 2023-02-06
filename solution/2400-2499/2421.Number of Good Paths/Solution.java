class Solution {
    private int[] p;

    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        int n = vals.length;
        p = new int[n];
        int[][] arr = new int[n][2];
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        Map<Integer, Map<Integer, Integer>> size = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            arr[i] = new int[] {vals[i], i};
            size.computeIfAbsent(i, k -> new HashMap<>()).put(vals[i], 1);
        }
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        int ans = n;
        for (var e : arr) {
            int v = e[0], a = e[1];
            for (int b : g[a]) {
                if (vals[b] > v) {
                    continue;
                }
                int pa = find(a), pb = find(b);
                if (pa != pb) {
                    ans += size.get(pa).getOrDefault(v, 0) * size.get(pb).getOrDefault(v, 0);
                    p[pa] = pb;
                    size.get(pb).put(
                        v, size.get(pb).getOrDefault(v, 0) + size.get(pa).getOrDefault(v, 0));
                }
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