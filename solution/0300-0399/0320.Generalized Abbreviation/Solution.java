class Solution {
    private List<String> ans;

    public List<String> generateAbbreviations(String word) {
        ans = new ArrayList<>();
        List<String> t = new ArrayList<>();
        dfs(word, t);
        return ans;
    }

    private void dfs(String s, List<String> t) {
        if ("".equals(s)) {
            ans.add(String.join("", t));
            return;
        }
        for (int i = 1; i < s.length() + 1; ++i) {
            t.add(i + "");
            if (i < s.length()) {
                t.add(String.valueOf(s.charAt(i)));
                dfs(s.substring(i + 1), t);
                t.remove(t.size() - 1);
            } else {
                dfs(s.substring(i), t);
            }
            t.remove(t.size() - 1);
        }
        t.add(String.valueOf(s.charAt(0)));
        dfs(s.substring(1), t);
        t.remove(t.size() - 1);
    }
}