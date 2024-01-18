class Trie {
    Trie[] children = new Trie[26];
    boolean isEnd;

    void insert(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                node.children[c] = new Trie();
            }
            node = node.children[c];
        }
        node.isEnd = true;
    }
}

class Solution {
    private int maxL;
    private int maxS;
    private String[] ans;
    private Trie trie = new Trie();
    private List<String> t = new ArrayList<>();

    public String[] maxRectangle(String[] words) {
        Map<Integer, List<String>> d = new HashMap<>(100);
        for (String w : words) {
            maxL = Math.max(maxL, w.length());
            trie.insert(w);
            d.computeIfAbsent(w.length(), k -> new ArrayList<>()).add(w);
        }
        for (List<String> ws : d.values()) {
            t.clear();
            dfs(ws);
        }
        return ans;
    }

    private void dfs(List<String> ws) {
        if (ws.get(0).length() * maxL <= maxS || t.size() >= maxL) {
            return;
        }
        for (String w : ws) {
            t.add(w);
            int st = check(t);
            if (st == 0) {
                t.remove(t.size() - 1);
                continue;
            }
            if (st == 1 && maxS < t.size() * t.get(0).length()) {
                maxS = t.size() * t.get(0).length();
                ans = t.toArray(new String[0]);
            }
            dfs(ws);
            t.remove(t.size() - 1);
        }
    }

    private int check(List<String> mat) {
        int m = mat.size(), n = mat.get(0).length();
        int ans = 1;
        for (int j = 0; j < n; ++j) {
            Trie node = trie;
            for (int i = 0; i < m; ++i) {
                int idx = mat.get(i).charAt(j) - 'a';
                if (node.children[idx] == null) {
                    return 0;
                }
                node = node.children[idx];
            }
            if (!node.isEnd) {
                ans = 2;
            }
        }
        return ans;
    }
}