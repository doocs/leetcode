class Solution {
    public String freqAlphabets(String s) {
        int i = 0, n = s.length();
        StringBuilder ans = new StringBuilder();
        while (i < n) {
            if (i + 2 < n && s.charAt(i + 2) == '#') {
                ans.append((char) ('a' + Integer.parseInt(s.substring(i, i + 2)) - 1));
                i += 3;
            } else {
                ans.append((char) ('a' + Integer.parseInt(s.substring(i, i + 1)) - 1));
                i++;
            }
        }
        return ans.toString();
    }
}
