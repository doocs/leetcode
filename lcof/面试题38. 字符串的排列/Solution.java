class Solution {
    private List<String> ans = new ArrayList<>();
    private char[] cs;

    public String[] permutation(String s) {
        cs = s.toCharArray();
        dfs(0);
        return ans.toArray(new String[ans.size()]);
    }

    private void dfs(int i) {
        if (i == cs.length - 1) {
            ans.add(String.valueOf(cs));
            return;
        }
        Set<Character> vis = new HashSet<>();
        for (int j = i; j < cs.length; ++j) {
            if (vis.add(cs[j])) {
                swap(i, j);
                dfs(i + 1);
                swap(i, j);
            }
        }
    }

    private void swap(int i, int j) {
        char t = cs[i];
        cs[i] = cs[j];
        cs[j] = t;
    }
}