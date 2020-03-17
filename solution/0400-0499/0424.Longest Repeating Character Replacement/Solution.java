class Solution {
    public int characterReplacement(String s, int k) {
        char[] cs = s.toCharArray();
        int[] map = new int[26];
        int res = 0;
        int max = 0;
        for (int l = 0, r = 0; r < cs.length; ) {
            max = Math.max(max, ++map[cs[r++] - 'A']);
            while (r - l - max > k) {
                --map[cs[l++] - 'A'];
            }
            res = Math.max(res, r - l);
        }
        return res;
    }
}
