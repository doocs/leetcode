class Solution {
    public int vowelConsonantScore(String s) {
        int v = 0, c = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isLetter(ch)) {
                c++;
                if ("aeiou".indexOf(ch) != -1) {
                    v++;
                }
            }
        }
        c -= v;
        return c == 0 ? 0 : v / c;
    }
}
