class Solution {
    public int maximumNumberOfStringPairs(String[] words) {
        Map<String, Integer> cnt = new HashMap<>(words.length);
        int ans = 0;
        for (String w : words) {
            ans += cnt.getOrDefault(w, 0);
            cnt.merge(new StringBuilder(w).reverse().toString(), 1, Integer::sum);
        }
        return ans;
    }
}