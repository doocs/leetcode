public class Trie {
    bool isEnd;
    Trie[] children = new Trie[26];

    public Trie() {

    }

    public void Insert(string word) {
        Trie node = this;
        foreach (var c in word)
        {
            var idx = c - 'a';
            node.children[idx] ??= new Trie();
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    public bool Search(string word) {
        Trie node = SearchPrefix(word);
        return node != null && node.isEnd;
    }

    public bool StartsWith(string prefix) {
        Trie node = SearchPrefix(prefix);
        return node != null;
    }

    private Trie SearchPrefix(string s) {
        Trie node = this;
        foreach (var c in s)
        {
            var idx = c - 'a';
            if (node.children[idx] == null)
            {
                return null;
            }
            node = node.children[idx];
        }
        return node;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.Insert(word);
 * bool param_2 = obj.Search(word);
 * bool param_3 = obj.StartsWith(prefix);
 */