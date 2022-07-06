class Trie {
    Trie[] children = new Trie[26];
    String root;
}

class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        Trie trie = new Trie();
        for (String root : dictionary) {
            Trie cur = trie;
            for (char c : root.toCharArray()) {
                c -= 'a';
                if (cur.children[c] == null) {
                    cur.children[c] = new Trie();
                }
                cur = cur.children[c];
            }
            cur.root = root;
        }
        List<String> ans = new ArrayList<>();
        for (String word : sentence.split(" ")) {
            Trie cur = trie;
            for (char c : word.toCharArray()) {
                c -= 'a';
                if (cur.children[c] == null || cur.root != null) {
                    break;
                }
                cur = cur.children[c];
            }
            ans.add(cur.root == null ? word : cur.root);
        }
        return String.join(" ", ans);
    }
}