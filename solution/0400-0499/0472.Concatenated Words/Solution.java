class Solution {
    private Trie trie;

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        List<String> ans = new ArrayList<>();
        trie = new Trie();
        for (String word : words) {
            if ("".equals(word)) {
                continue;
            }
            if (dfs(word, 0)) {
                ans.add(word);
            } else {
                insert(word);
            }
        }
        return ans;
    }

    private boolean dfs(String word, int u) {
        if (word.length() == u) {
            return true;
        }
        Trie node = trie;
        for (int i = u; i < word.length(); ++i) {
            int idx = word.charAt(i) - 'a';
            node = node.children[idx];
            if (node == null) {
                return false;
            }
            if (node.isEnd) {
                if (dfs(word, i + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void insert(String word) {
        Trie node = trie;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
        }
        node.isEnd = true;
    }
}

class Trie {
    Trie[] children = new Trie[26];
    boolean isEnd;
}