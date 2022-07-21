class Trie {
    Trie[] children = new Trie[26];
    int[] v = new int[26];

    void insert(String w) {
        Trie node = this;
        int t = w.charAt(w.length() - 1) - 'a';
        for (char c : w.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                node.children[c] = new Trie();
            }
            node = node.children[c];
            node.v[t]++;
        }
    }

    String search(String w) {
        Trie node = this;
        StringBuilder res = new StringBuilder();
        int t = w.charAt(w.length() - 1) - 'a';
        for (int i = 0; i < w.length() - 1; ++i) {
            char c = w.charAt(i);
            node = node.children[c - 'a'];
            res.append(c);
            if (node.v[t] == 1) {
                break;
            }
        }
        int n = w.length() - res.length() - 1;
        if (n > 0) {
            res.append(n);
        }
        res.append(w.charAt(w.length() - 1));
        return res.length() < w.length() ? res.toString() : w;
    }
}

class Solution {
    public List<String> wordsAbbreviation(List<String> words) {
        Map<Integer, Trie> trees = new HashMap<>();
        for (String w : words) {
            if (!trees.containsKey(w.length())) {
                trees.put(w.length(), new Trie());
            }
        }
        for (String w : words) {
            trees.get(w.length()).insert(w);
        }
        List<String> ans = new ArrayList<>();
        for (String w : words) {
            ans.add(trees.get(w.length()).search(w));
        }
        return ans;
    }
}