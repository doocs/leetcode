public class Solution {
    public bool WordBreak(string s, IList<string> wordDict) {
        Trie trie = new Trie();
        foreach (string w in wordDict) {
            trie.Insert(w);
        }
        int n = s.Length;
        bool[] f = new bool[n + 1];
        f[n] = true;
        for (int i = n - 1; i >= 0; --i) {
            Trie node = trie;
            for (int j = i; j < n; ++j) {
                int k = s[j] - 'a';
                if (node.Children[k] == null) {
                    break;
                }
                node = node.Children[k];
                if (node.IsEnd && f[j + 1]) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[0];
    }
}

class Trie {
    public Trie[] Children { get; set; }
    public bool IsEnd { get; set; }

    public Trie() {
        Children = new Trie[26];
        IsEnd = false;
    }

    public void Insert(string word) {
        Trie node = this;
        foreach (char c in word) {
            int i = c - 'a';
            if (node.Children[i] == null) {
                node.Children[i] = new Trie();
            }
            node = node.Children[i];
        }
        node.IsEnd = true;
    }
}
