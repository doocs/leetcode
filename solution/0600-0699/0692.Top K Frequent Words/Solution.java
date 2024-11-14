class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String w : words) {
            cnt.merge(w, 1, Integer::sum);
        }
        Arrays.sort(words, (a, b) -> {
            int c1 = cnt.get(a), c2 = cnt.get(b);
            return c1 == c2 ? a.compareTo(b) : c2 - c1;
        });
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < words.length && ans.size() < k; ++i) {
            if (i == 0 || !words[i].equals(words[i - 1])) {
                ans.add(words[i]);
            }
        }
        return ans;
    }
}
