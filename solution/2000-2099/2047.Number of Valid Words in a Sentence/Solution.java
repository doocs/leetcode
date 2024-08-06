class Solution {
    public int countValidWords(String sentence) {
        int ans = 0;
        for (String s : sentence.split(" ")) {
            ans += check(s.toCharArray());
        }
        return ans;
    }

    private int check(char[] s) {
        if (s.length == 0) {
            return 0;
        }
        boolean st = false;
        for (int i = 0; i < s.length; ++i) {
            if (Character.isDigit(s[i])) {
                return 0;
            }
            if ((s[i] == '!' || s[i] == '.' || s[i] == ',') && i < s.length - 1) {
                return 0;
            }
            if (s[i] == '-') {
                if (st || i == 0 || i == s.length - 1) {
                    return 0;
                }
                if (!Character.isAlphabetic(s[i - 1]) || !Character.isAlphabetic(s[i + 1])) {
                    return 0;
                }
                st = true;
            }
        }
        return 1;
    }
}
