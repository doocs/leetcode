class Solution {
    public int characterReplacement(String s, int k) {
        int[] counter = new int[26];
        int i = 0;
        int j = 0;
        for (int maxCnt = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            ++counter[c - 'A'];
            maxCnt = Math.max(maxCnt, counter[c - 'A']);
            if (i - j + 1 - maxCnt > k) {
                --counter[s.charAt(j) - 'A'];
                ++j;
            }
        }
        return i - j;
    }
}