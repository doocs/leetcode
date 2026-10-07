class Solution {
    public List<String> computeSimilarities(int[][] docs) {
        int n = docs.length;
        Map<Integer, List<Integer>> d = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            for (int x : docs[i]) {
                d.computeIfAbsent(x, k -> new ArrayList<>()).add(i);
            }
        }
        Map<Long, Integer> cnt = new HashMap<>();
        for (List<Integer> ids : d.values()) {
            int m = ids.size();
            for (int i = 0; i < m; ++i) {
                for (int j = i + 1; j < m; ++j) {
                    long key = 1L * ids.get(i) * n + ids.get(j);
                    cnt.merge(key, 1, Integer::sum);
                }
            }
        }
        List<String> ans = new ArrayList<>();
        for (var e : cnt.entrySet()) {
            long key = e.getKey();
            int v = e.getValue();
            int i = (int) (key / n), j = (int) (key % n);
            int tot = docs[i].length + docs[j].length - v;
            double x = (double) v / tot + 1e-9;
            ans.add(String.format("%d,%d: %.4f", i, j, x));
        }
        return ans;
    }
}
