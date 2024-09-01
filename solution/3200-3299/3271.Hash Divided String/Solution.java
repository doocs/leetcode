class Solution {
    public String stringHash(String s, int k) {
        StringBuilder ans = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i += k) {
            int t = 0;
            for (int j = i; j < i + k; ++j) {
                t += s.charAt(j) - 'a';
            }
            int hashedChar = t % 26;
            ans.append((char) ('a' + hashedChar));
        }
        return ans.toString();
    }
}
