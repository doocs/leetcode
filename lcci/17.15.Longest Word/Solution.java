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

    boolean search(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                return false;
            }
            node = node.children[c];
        }
        return node.isEnd;
    }
}

class Solution {
    private Trie trie = new Trie();

    public String longestWord(String[] words) {
        Arrays.sort(words, (a, b) -> {
            if (a.length() != b.length()) {
                return a.length() - b.length();
            }
            return b.compareTo(a);
        });
        String ans = "";
        for (String w : words) {
            if (dfs(w)) {
                ans = w;
            }
            trie.insert(w);
        }
        return ans;
    }

    private boolean dfs(String w) {
        if ("".equals(w)) {
            return true;
        }
        for (int i = 1; i <= w.length(); ++i) {
            if (trie.search(w.substring(0, i)) && dfs(w.substring(i))) {
                return true;
            }
        }
        return false;
    }
}