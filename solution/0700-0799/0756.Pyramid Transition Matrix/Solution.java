class Solution {
    private int[][] f = new int[7][7];
    private Map<String, Boolean> dp = new HashMap<>();

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        for (String s : allowed) {
            int a = s.charAt(0) - 'A', b = s.charAt(1) - 'A';
            f[a][b] |= 1 << (s.charAt(2) - 'A');
        }
        return dfs(bottom, new StringBuilder());
    }

    boolean dfs(String s, StringBuilder t) {
        if (s.length() == 1) {
            return true;
        }
        if (t.length() + 1 == s.length()) {
            return dfs(t.toString(), new StringBuilder());
        }
        String k = s + "." + t.toString();
        if (dp.containsKey(k)) {
            return dp.get(k);
        }
        int a = s.charAt(t.length()) - 'A', b = s.charAt(t.length() + 1) - 'A';
        int cs = f[a][b];
        for (int i = 0; i < 7; ++i) {
            if (((cs >> i) & 1) == 1) {
                t.append((char) ('A' + i));
                if (dfs(s, t)) {
                    dp.put(k, true);
                    return true;
                }
                t.deleteCharAt(t.length() - 1);
            }
        }
        dp.put(k, false);
        return false;
    }
}