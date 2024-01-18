class Solution {
    public String[] permutation(String S) {
        Set<Character> vis = new HashSet<>();
        List<String> ans = new ArrayList<>();
        StringBuilder t = new StringBuilder();
        dfs(0, S, t, ans, vis);
        return ans.toArray(new String[0]);
    }

    private void dfs(int u, String S, StringBuilder t, List<String> ans, Set<Character> vis) {
        if (u == S.length()) {
            ans.add(t.toString());
            return;
        }
        for (char c : S.toCharArray()) {
            if (vis.contains(c)) {
                continue;
            }
            vis.add(c);
            t.append(c);
            dfs(u + 1, S, t, ans, vis);
            t.deleteCharAt(t.length() - 1);
            vis.remove(c);
        }
    }
}