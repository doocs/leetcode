class Solution {
    public boolean canMakeSubsequence(String s, String t) {
        int m = s.length(), n = t.length();
        int i0 = 0, i1 = 0, j = 0;

        while (i1 < m && j < n) {
            if (s.charAt(i1) == t.charAt(j)) {
                i1++;
            }

            i1 = Math.max(i1, i0 + 1);

            if (s.charAt(i0) == t.charAt(j)) {
                i0++;
            }

            j++;
        }

        return i1 == m;
    }
}