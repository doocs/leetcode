class Solution {
    private List<String> ans = new ArrayList<>();

    public String getHappyString(int n, int k) {
        dfs("", n);
        return ans.size() < k ? "" : ans.get(k - 1);
    }

    private void dfs(String t, int n) {
        if (t.length() == n) {
            ans.add(t);
            return;
        }
        for (char c : "abc".toCharArray()) {
            if (t.length() > 0 && t.charAt(t.length() - 1) == c) {
                continue;
            }
            dfs(t + c, n);
        }
    }
}