class Solution {
    private List<String> ans = new ArrayList<>();
    private List<String> wordList;
    private String endWord;
    private boolean[] vis;

    public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
        this.wordList = wordList;
        this.endWord = endWord;
        ans.add(beginWord);
        vis = new boolean[wordList.size()];
        return dfs(beginWord) ? ans : List.of();
    }

    private boolean dfs(String s) {
        if (s.equals(endWord)) {
            return true;
        }
        for (int i = 0; i < wordList.size(); ++i) {
            String t = wordList.get(i);
            if (vis[i] || !check(s, t)) {
                continue;
            }
            vis[i] = true;
            ans.add(t);
            if (dfs(t)) {
                return true;
            }
            ans.remove(ans.size() - 1);
        }
        return false;
    }

    private boolean check(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int cnt = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != t.charAt(i)) {
                ++cnt;
            }
        }
        return cnt == 1;
    }
}