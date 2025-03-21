public class Trie {
    private Trie[] children = new Trie[26];
    private int val;

    public void Insert(string w, int x) {
        Trie node = this;
        for (int i = 0; i < w.Length; ++i) {
            int idx = w[i] - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
            node.val += x;
        }
    }

    public int Search(string w) {
        Trie node = this;
        for (int i = 0; i < w.Length; ++i) {
            int idx = w[i] - 'a';
            if (node.children[idx] == null) {
                return 0;
            }
            node = node.children[idx];
        }
        return node.val;
    }
}

public class MapSum {
    private Dictionary<string, int> d = new Dictionary<string, int>();
    private Trie trie = new Trie();

    public MapSum() {
    }

    public void Insert(string key, int val) {
        int x = val - (d.ContainsKey(key) ? d[key] : 0);
        d[key] = val;
        trie.Insert(key, x);
    }

    public int Sum(string prefix) {
        return trie.Search(prefix);
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.Insert(key,val);
 * int param_2 = obj.Sum(prefix);
 */
