class Solution {
    public int findClosest(String[] words, String word1, String word2) {
        Map<String, List<Integer>> d = new HashMap<>();
        for (int i = 0; i < words.length; ++i) {
            d.computeIfAbsent(words[i], k -> new ArrayList<>()).add(i);
        }
        List<Integer> idx1 = d.get(word1), idx2 = d.get(word2);
        int i = 0, j = 0, m = idx1.size(), n = idx2.size();
        int ans = 100000;
        while (i < m && j < n) {
            int t = Math.abs(idx1.get(i) - idx2.get(j));
            ans = Math.min(ans, t);
            if (idx1.get(i) < idx2.get(j)) {
                ++i;
            } else {
                ++j;
            }
        }
        return ans;
    }
}