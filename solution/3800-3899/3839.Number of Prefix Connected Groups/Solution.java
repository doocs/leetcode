class Solution {
    public int prefixConnected(String[] words, int k) {
        Map<String, Integer> cnt = new HashMap<>();
        for (var w : words) {
            if (w.length() >= k) {
                cnt.merge(w.substring(0, k), 1, Integer::sum);
            }
        }
        int ans = 0;
        for (var v : cnt.values()) {
            if (v > 1) {
                ++ans;
            }
        }
        return ans;
    }
}
