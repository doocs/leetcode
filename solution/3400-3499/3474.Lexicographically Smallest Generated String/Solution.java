class Solution {
    public String generateString(String s, String t) {
        int n = s.length(), m = t.length();
        char[] ans = new char[n + m - 1];
        boolean[] fixed = new boolean[n + m - 1];

        Arrays.fill(ans, 'a');

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != 'T') {
                continue;
            }
            for (int j = 0; j < m; j++) {
                int k = i + j;
                if (fixed[k] && ans[k] != t.charAt(j)) {
                    return "";
                }
                ans[k] = t.charAt(j);
                fixed[k] = true;
            }
        }

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != 'F') {
                continue;
            }

            boolean same = true;
            for (int j = 0; j < m; j++) {
                if (ans[i + j] != t.charAt(j)) {
                    same = false;
                    break;
                }
            }
            if (!same) {
                continue;
            }

            boolean ok = false;
            for (int j = i + m - 1; j >= i; j--) {
                if (!fixed[j]) {
                    ans[j] = 'b';
                    ok = true;
                    break;
                }
            }
            if (!ok) {
                return "";
            }
        }

        return new String(ans);
    }
}
