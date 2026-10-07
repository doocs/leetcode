class Solution {
    private String exp;
    private int i;

    public List<String> braceExpansionII(String expression) {
        exp = expression;
        i = 0;
        List<String> ans = new ArrayList<>(expr());
        Collections.sort(ans);
        return ans;
    }

    private Set<String> expr() {
        Set<String> res = term();
        while (i < exp.length() && exp.charAt(i) == ',') {
            ++i;
            res.addAll(term());
        }
        return res;
    }

    private Set<String> term() {
        Set<String> res = new HashSet<>();
        res.add("");
        while (i < exp.length() && exp.charAt(i) != ',' && exp.charAt(i) != '}') {
            Set<String> cur = new HashSet<>();
            if (exp.charAt(i) == '{') {
                ++i;
                cur = expr();
                ++i;
            } else {
                int j = i + 1;
                while (j < exp.length() && exp.charAt(j) >= 'a' && exp.charAt(j) <= 'z') {
                    ++j;
                }
                cur.add(exp.substring(i, j));
                i = j;
            }
            Set<String> nxt = new HashSet<>();
            for (String a : res) {
                for (String b : cur) {
                    nxt.add(a + b);
                }
            }
            res = nxt;
        }
        return res;
    }
}
