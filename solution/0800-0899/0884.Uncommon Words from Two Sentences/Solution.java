class Solution {
    public String[] uncommonFromSentences(String s1, String s2) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String s : s1.split(" ")) {
            cnt.merge(s, 1, Integer::sum);
        }
        for (String s : s2.split(" ")) {
            cnt.merge(s, 1, Integer::sum);
        }
        List<String> ans = new ArrayList<>();
        for (var e : cnt.entrySet()) {
            if (e.getValue() == 1) {
                ans.add(e.getKey());
            }
        }
        return ans.toArray(new String[0]);
    }
}