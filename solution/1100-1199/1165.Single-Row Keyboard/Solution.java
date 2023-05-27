class Solution {
    public int calculateTime(String keyboard, String word) {
        int[] pos = new int[26];
        for (int i = 0; i < 26; ++i) {
            pos[keyboard.charAt(i) - 'a'] = i;
        }
        int ans = 0, i = 0;
        for (int k = 0; k < word.length(); ++k) {
            int j = pos[word.charAt(k) - 'a'];
            ans += Math.abs(i - j);
            i = j;
        }
        return ans;
    }
}