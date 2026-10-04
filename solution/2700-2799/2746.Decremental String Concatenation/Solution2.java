class Solution {
    public int minimizeConcatenatedLength(String[] words) {
        int n = words.length;
        int[][][] f = new int[n + 1][26][26];
        for (int i = n - 1; i > 0; --i) {
            String s = words[i];
            int m = s.length();
            int c = s.charAt(0) - 'a';
            int d = s.charAt(m - 1) - 'a';
            for (int a = 0; a < 26; ++a) {
                for (int b = 0; b < 26; ++b) {
                    int x = f[i + 1][a][d] - (c == b ? 1 : 0);
                    int y = f[i + 1][c][b] - (d == a ? 1 : 0);
                    f[i][a][b] = m + Math.min(x, y);
                }
            }
        }
        int a = words[0].charAt(0) - 'a';
        int b = words[0].charAt(words[0].length() - 1) - 'a';
        return words[0].length() + f[1][a][b];
    }
}
