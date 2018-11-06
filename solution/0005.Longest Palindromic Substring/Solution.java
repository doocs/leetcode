class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        String str = "";
        char[] chars = s.toCharArray();
        int len = chars.length;
        boolean[][] res = new boolean[len][len];
        int start = 0;
        int max = 1;
        for (int i = 0; i < len; ++i) {
            for (int j = 0; j <= i; ++j) {

                res[j][i] = i - j < 2 ? chars[j] == chars[i] : res[j + 1][i - 1] && chars[j] == chars[i];

                if (res[j][i] && max < i - j + 1) {
                    max = i - j + 1;
                    start = j;
                }

            }
        }

        return s.substring(start, start + max);

    }
}