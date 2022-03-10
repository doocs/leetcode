class Solution {
    public String[] uncommonFromSentences(String s1, String s2) {
        Map<String, Integer> counter = new HashMap<>();
        add(s1, counter);
        add(s2, counter);
        List<String> ans = new ArrayList<>();
        for (Map.Entry<String, Integer> e : counter.entrySet()) {
            if (e.getValue() == 1) {
                ans.add(e.getKey());
            }
        }
        return ans.toArray(new String[0]);
    }

    private void add(String s, Map<String, Integer> counter) {
        for (String w : s.split(" ")) {
            counter.put(w, counter.getOrDefault(w, 0) + 1);
        }
    }
}