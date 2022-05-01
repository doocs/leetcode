class Solution {
    public int minimumCardPickup(int[] cards) {
        Map<Integer, Integer> m = new HashMap<>();
        int ans = 1000000;
        for (int i = 0; i < cards.length; ++i) {
            int c = cards[i];
            if (m.containsKey(c)) {
                ans = Math.min(ans, i - m.get(c) + 1);
            }
            m.put(c, i);
        }
        return ans == 1000000 ? -1 : ans;
    }
}