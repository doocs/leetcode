class Solution {
    public String rankTeams(String[] votes) {
        Map<Character, int[]> counter = new HashMap<>();
        int n = votes[0].length();
        for (String vote : votes) {
            for (int i = 0; i < n; ++i) {
                char v = vote.charAt(i);
                counter.computeIfAbsent(v, k -> new int[26])[i]++;
            }
        }
        List<Map.Entry<Character, int[]>> t = new ArrayList<>(counter.entrySet());
        Collections.sort(t, (a, b) -> {
            int[] v1 = a.getValue();
            int[] v2 = b.getValue();
            for (int i = 0; i < 26; ++i) {
                if (v1[i] != v2[i]) {
                    return v2[i] - v1[i];
                }
            }
            return a.getKey() - b.getKey();
        });
        StringBuilder ans = new StringBuilder();
        t.forEach(e -> ans.append(e.getKey()));
        return ans.toString();
    }
}