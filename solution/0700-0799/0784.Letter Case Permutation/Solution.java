class Solution {
    private List<String> ans = new ArrayList<>();
    private char[] t;

    public List<String> letterCasePermutation(String s) {
        t = s.toCharArray();
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i >= t.length) {
            ans.add(String.valueOf(t));
            return;
        }
        dfs(i + 1);
        if (t[i] >= 'A') {
            t[i] ^= 32;
            dfs(i + 1);
        }
    }
}