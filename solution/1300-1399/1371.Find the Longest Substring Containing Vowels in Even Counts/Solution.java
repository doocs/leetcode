class Solution {
    public int findTheLongestSubstring(String s) {
        String vowels = "aeiou";
        int[] d = new int[32];
        Arrays.fill(d, 1 << 29);
        d[0] = 0;
        int ans = 0, mask = 0;
        for (int i = 1; i <= s.length(); ++i) {
            char c = s.charAt(i - 1);
            for (int j = 0; j < 5; ++j) {
                if (c == vowels.charAt(j)) {
                    mask ^= 1 << j;
                    break;
                }
            }
            ans = Math.max(ans, i - d[mask]);
            d[mask] = Math.min(d[mask], i);
        }
        return ans;
    }
}
