class Trie {
    Trie[] children = new Trie[26];

    int insert(String w) {
        Trie node = this;
        boolean pref = true;
        for (int i = w.length() - 1; i >= 0; --i) {
            int idx = w.charAt(i) - 'a';
            if (node.children[idx] == null) {
                pref = false;
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
        }
        return pref ? 0 : w.length() + 1;
    }
}

class Solution {
    public int minimumLengthEncoding(String[] words) {
        Arrays.sort(words, (a, b) -> b.length() - a.length());
        int ans = 0;
        Trie trie = new Trie();
        for (String w : words) {
            ans += trie.insert(w);
        }
        return ans;
    }
}