class Solution {
    public String[] sortFeatures(String[] features, String[] responses) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String s : responses) {
            Set<String> vis = new HashSet<>();
            for (String w : s.split(" ")) {
                if (vis.add(w)) {
                    cnt.merge(w, 1, Integer::sum);
                }
            }
        }
        int n = features.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> {
            int x = cnt.getOrDefault(features[i], 0);
            int y = cnt.getOrDefault(features[j], 0);
            return x == y ? i - j : y - x;
        });
        String[] ans = new String[n];
        for (int i = 0; i < n; i++) {
            ans[i] = features[idx[i]];
        }
        return ans;
    }
}