class Solution {
    public int[] masterMind(String solution, String guess) {
        int x = 0, y = 0;
        Map<Character, Integer> cnt1 = new HashMap<>();
        Map<Character, Integer> cnt2 = new HashMap<>();
        for (int i = 0; i < 4; ++i) {
            char a = solution.charAt(i), b = guess.charAt(i);
            x += a == b ? 1 : 0;
            cnt1.put(a, cnt1.getOrDefault(a, 0) + 1);
            cnt2.put(b, cnt2.getOrDefault(b, 0) + 1);
        }
        for (char c : "RYGB".toCharArray()) {
            y += Math.min(cnt1.getOrDefault(c, 0), cnt2.getOrDefault(c, 0));
        }
        return new int[] {x, y - x};
    }
}