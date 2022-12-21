class Solution {
    public String[] sortFeatures(String[] features, String[] responses) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String r : responses) {
            Set<String> ws = new HashSet<>();
            for (String w : r.split(" ")) {
                ws.add(w);
            }
            for (String w : ws) {
                cnt.put(w, cnt.getOrDefault(w, 0) + 1);
            }
        }
        int n = features.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; ++i) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> {
            int d = cnt.getOrDefault(features[j], 0) - cnt.getOrDefault(features[i], 0);
            return d == 0 ? i - j : d;
        });
        String[] ans = new String[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = features[idx[i]];
        }
        return ans;
    }
}