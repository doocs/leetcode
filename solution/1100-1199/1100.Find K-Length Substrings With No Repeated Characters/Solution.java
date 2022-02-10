class Solution {
    public int numKLenSubstrNoRepeats(String s, int k) {
        int ans = 0;
        Map<Character, Integer> mp = new HashMap<>();
        for (int i = 0, j = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (mp.containsKey(c)) {
                j = Math.max(j, mp.get(c) + 1);
            }
            mp.put(c, i);
            if (i - j + 1 >= k) {
                ++ans;
            }
        }
        return ans;
    }
}