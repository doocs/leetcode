class Solution {
    public int[] validSequence(String word1, String word2) {
        int m = word1.length(), n = word2.length();

        int[] suf = new int[m + 1];
        suf[m] = n;

        int j = n - 1;
        for (int i = m - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                j--;
            }
            suf[i] = j + 1;
        }

        int[] ans = new int[n];
        int size = 0;
        boolean changed = false;
        j = 0;

        for (int i = 0; i < m; i++) {
            char c = word1.charAt(i);
            if (c == word2.charAt(j) || (!changed && suf[i + 1] <= j + 1)) {
                if (c != word2.charAt(j)) {
                    changed = true;
                }
                ans[size++] = i;
                j++;
                if (j == n) {
                    return ans;
                }
            }
        }

        return new int[0];
    }
}