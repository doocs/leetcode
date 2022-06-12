class Solution {
    public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> cnt1 = count(words1);
        Map<String, Integer> cnt2 = count(words2);
        int ans = 0;
        for (String w : words1) {
            if (cnt1.getOrDefault(w, 0) == 1 && cnt2.getOrDefault(w, 0) == 1) {
                ++ans;
            }
        }
        return ans;
    }

    private Map<String, Integer> count(String[] words) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String w : words) {
            cnt.put(w, cnt.getOrDefault(w, 0) + 1);
        }
        return cnt;
    }
}