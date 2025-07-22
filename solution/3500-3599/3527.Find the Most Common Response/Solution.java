class Solution {
    public String findCommonResponse(List<List<String>> responses) {
        Map<String, Integer> cnt = new HashMap<>();
        for (var ws : responses) {
            Set<String> s = new HashSet<>();
            for (var w : ws) {
                if (s.add(w)) {
                    cnt.merge(w, 1, Integer::sum);
                }
            }
        }
        String ans = responses.get(0).get(0);
        for (var e : cnt.entrySet()) {
            String w = e.getKey();
            int v = e.getValue();
            if (cnt.get(ans) < v || (cnt.get(ans) == v && w.compareTo(ans) < 0)) {
                ans = w;
            }
        }
        return ans;
    }
}