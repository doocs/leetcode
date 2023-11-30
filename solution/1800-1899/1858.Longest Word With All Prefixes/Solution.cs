public class Trie {
    private Trie[] children = new Trie[26];
    private bool isEnd;

    public Trie() { }

    public void Insert(string w) {
        Trie node = this;
        foreach (char c in w.ToCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    public bool Search(string w) {
        Trie node = this;
        foreach (char c in w.ToCharArray()) {
            int idx = c - 'a';
            node = node.children[idx];
            if (!node.isEnd) {
                return false;
            }
        }
        return true;
    }
}

public class Solution {
    public string LongestWord(string[] words) {
        Trie trie = new Trie();
        foreach (string w in words) {
            trie.Insert(w);
        }

        string ans = "";
        foreach (string w in words) {
            if ((w.Length > ans.Length || (w.Length == ans.Length && string.Compare(w, ans) < 0)) && trie.Search(w)) {
                ans = w;
            }
        }
        return ans;
    }
}