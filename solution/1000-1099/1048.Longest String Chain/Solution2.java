class Solution {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        Map<String, Integer> f = new HashMap<>();
        int ans = 0;
        for (String w : words) {
            int x = 1;
            for (int i = 0; i < w.length(); ++i) {
                String pred = w.substring(0, i) + w.substring(i + 1);
                x = Math.max(x, f.getOrDefault(pred, 0) + 1);
            }
            f.put(w, x);
            ans = Math.max(ans, x);
        }
        return ans;
    }
}
