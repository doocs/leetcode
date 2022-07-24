class Solution {
    public String bestHand(int[] ranks, char[] suits) {
        Set<Character> s = new HashSet<>();
        for (char c : suits) {
            s.add(c);
        }
        if (s.size() == 1) {
            return "Flush";
        }
        int[] cnt = new int[20];
        for (int v : ranks) {
            ++cnt[v];
            if (cnt[v] >= 3) {
                return "Three of a Kind";
            }
        }
        for (int v : cnt) {
            if (v == 2) {
                return "Pair";
            }
        }
        return "High Card";
    }
}