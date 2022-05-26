class Solution {
    private int tdel;
    private String s;
    private Set<String> ans;

    public List<String> removeInvalidParentheses(String s) {
        int ldel = 0, rdel = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                ++ldel;
            } else if (c == ')') {
                if (ldel == 0) {
                    ++rdel;
                } else {
                    --ldel;
                }
            }
        }
        tdel = ldel + rdel;
        this.s = s;
        ans = new HashSet<>();
        dfs(0, "", 0, 0, ldel, rdel);
        return new ArrayList<>(ans);
    }

    private void dfs(int i, String t, int lcnt, int rcnt, int ldel, int rdel) {
        if (ldel * rdel < 0 || lcnt < rcnt || ldel + rdel > s.length() - i) {
            return;
        }
        if (ldel == 0 && rdel == 0) {
            if (s.length() - t.length() == tdel) {
                ans.add(t);
            }
        }
        if (i == s.length()) {
            return;
        }
        char c = s.charAt(i);
        if (c == '(') {
            dfs(i + 1, t, lcnt, rcnt, ldel - 1, rdel);
            dfs(i + 1, t + String.valueOf(c), lcnt + 1, rcnt, ldel, rdel);
        } else if (c == ')') {
            dfs(i + 1, t, lcnt, rcnt, ldel, rdel - 1);
            dfs(i + 1, t + String.valueOf(c), lcnt, rcnt + 1, ldel, rdel);
        } else {
            dfs(i + 1, t + String.valueOf(c), lcnt, rcnt, ldel, rdel);
        }
    }
}