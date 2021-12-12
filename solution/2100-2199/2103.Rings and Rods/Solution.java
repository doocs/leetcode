class Solution {
    public int countPoints(String rings) {
        Map<Integer, Set<Character>> mp = new HashMap<>();
        for (int i = 1; i < rings.length(); i += 2) {
            int c = rings.charAt(i) - '0';
            mp.computeIfAbsent(c, k -> new HashSet<>()).add(rings.charAt(i - 1));
        }
        int ans = 0;
        for (Set<Character> e : mp.values()) {
            if (e.size() == 3) {
                ++ans;
            }
        }
        return ans;
    }
}