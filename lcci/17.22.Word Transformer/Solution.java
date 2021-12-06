class Solution {
    private List<String> words;
    private List<String> ans;
    private Set<String> visited;

    public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
        words = wordList;
        ans = new ArrayList<>();
        visited = new HashSet<>();
        List<String> t = new ArrayList<>();
        t.add(beginWord);
        dfs(beginWord, endWord, t);
        return ans;
    }

    private void dfs(String begin, String end, List<String> t) {
        if (!ans.isEmpty()) {
            return;
        }
        if (Objects.equals(begin, end)) {
            ans = new ArrayList<>(t);
            return;
        }
        for (String word : words) {
            if (visited.contains(word) || !check(begin, word)) {
                continue;
            }
            t.add(word);
            visited.add(word);
            dfs(word, end, t);
            t.remove(t.size() - 1);
        }
    }

    private boolean check(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        int cnt = 0;
        for (int i = 0; i < a.length(); ++i) {
            if (a.charAt(i) != b.charAt(i)) {
                ++cnt;
            }
        }
        return cnt == 1;
    }
}