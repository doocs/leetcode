class Solution {
    public List<String> computeSimilarities(int[][] docs) {
        Map<Integer, List<Integer>> d = new HashMap<>();
        for (int i = 0; i < docs.length; ++i) {
            for (int v : docs[i]) {
                d.computeIfAbsent(v, k -> new ArrayList<>()).add(i);
            }
        }
        Map<String, Integer> cnt = new HashMap<>();
        for (var ids : d.values()) {
            int n = ids.size();
            for (int i = 0; i < n; ++i) {
                for (int j = i + 1; j < n; ++j) {
                    String k = ids.get(i) + "," + ids.get(j);
                    cnt.put(k, cnt.getOrDefault(k, 0) + 1);
                }
            }
        }
        List<String> ans = new ArrayList<>();
        for (var e : cnt.entrySet()) {
            String k = e.getKey();
            int v = e.getValue();
            String[] t = k.split(",");
            int i = Integer.parseInt(t[0]), j = Integer.parseInt(t[1]);
            int tot = docs[i].length + docs[j].length - v;
            double x = (double) v / tot;
            ans.add(String.format("%s: %.4f", k, x));
        }
        return ans;
    }
}