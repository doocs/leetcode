class Trie {
public:
    Trie()
        : children(26, nullptr) {
    }

    void insert(string& w, int x) {
        Trie* node = this;
        for (char c : w) {
            c -= 'a';
            if (!node->children[c]) {
                node->children[c] = new Trie();
            }
            node = node->children[c];
            node->val += x;
        }
    }

    int search(string& w) {
        Trie* node = this;
        for (char c : w) {
            c -= 'a';
            if (!node->children[c]) {
                return 0;
            }
            node = node->children[c];
        }
        return node->val;
    }

private:
    vector<Trie*> children;
    int val = 0;
};

class MapSum {
public:
    MapSum() {
    }

    void insert(string key, int val) {
        int x = val - d[key];
        d[key] = val;
        trie->insert(key, x);
    }

    int sum(string prefix) {
        return trie->search(prefix);
    }

private:
    unordered_map<string, int> d;
    Trie* trie = new Trie();
};

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum* obj = new MapSum();
 * obj->insert(key,val);
 * int param_2 = obj->sum(prefix);
 */