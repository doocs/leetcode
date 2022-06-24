class Solution {
    private Map<String, int[]> memo;

    public int countEval(String s, int result) {
        memo = new HashMap<>();
        int[] ans = dfs(s);
        return result == 0 || result == 1 ? ans[result] : 0;
    }

    private int[] dfs(String s) {
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        int[] res = new int[2];
        if (s.length() == 1) {
            res[Integer.parseInt(s)] = 1;
            return res;
        }
        for (int k = 0; k < s.length(); ++k) {
            char op = s.charAt(k);
            if (op == '&' || op == '|' || op == '^') {
                int[] left = dfs(s.substring(0, k));
                int[] right = dfs(s.substring(k + 1));
                for (int i = 0; i < 2; ++i) {
                    for (int j = 0; j < 2; ++j) {
                        int v = 0;
                        if (op == '&') {
                            v = i & j;
                        } else if (op == '|') {
                            v = i | j;
                        } else if (op == '^') {
                            v = i ^ j;
                        }
                        res[v] += left[i] * right[j];
                    }
                }
            }
        }
        memo.put(s, res);
        return res;
    }
}