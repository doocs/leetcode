class Solution {
    public boolean winnerOfGame(String colors) {
        int n = colors.length();
        int a = 0, b = 0;
        for (int i = 0, j = 0; i < n; i = j) {
            while (j < n && colors.charAt(j) == colors.charAt(i)) {
                ++j;
            }
            int m = j - i - 2;
            if (m > 0) {
                if (colors.charAt(i) == 'A') {
                    a += m;
                } else {
                    b += m;
                }
            }
        }
        return a > b;
    }
}