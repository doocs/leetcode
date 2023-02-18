class Solution {
    public String bestHand(int[] ranks, char[] suits) {
        Set<Character> s = new HashSet<>();
        for (char c : suits) {
            s.add(c);
        }
        if (s.size() == 1) {
            return "Flush";
        }
        int[] cnt = new int[14];
        boolean pair = false;
        for (int x : ranks) {
            if (++cnt[x] == 3) {
                return "Three of a Kind";
            }
            pair = pair || cnt[x] == 2;
        }
        return pair ? "Pair" : "High Card";
    }
}