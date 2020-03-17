class Solution {
    public int numDistinct(String s, String t) {
        int[][] hash = new int[256][t.length() + 1];
        int[] cnt = new int[t.length() + 1];
        cnt[0] = 1;
        for (int i = 0; i < t.length();) {
            char c = t.charAt(i);
            hash[c][++hash[c][0]] = ++i;
        }
        for(char c : s.toCharArray()) {
            for(int i = hash[c][0]; i > 0; i--) {
                cnt[hash[c][i]] += cnt[hash[c][i] - 1];
            }
        }
        return cnt[t.length()];
    }
}