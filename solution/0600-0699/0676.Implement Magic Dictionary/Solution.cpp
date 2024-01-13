class Trie {
private:
    Trie* children[26];
    bool isEnd = false;

public:
    Trie() {
        fill(begin(children), end(children), nullptr);
    }

    void insert(const string& w) {
        Trie* node = this;
        for (char c : w) {
            int i = c - 'a';
            if (!node->children[i]) {
                node->children[i] = new Trie();
            }
            node = node->children[i];
        }
        node->isEnd = true;
    }

    bool search(const string& w) {
        function<bool(int, Trie*, int)> dfs = [&](int i, Trie* node, int diff) {
            if (i >= w.size()) {
                return diff == 1 && node->isEnd;
            }
            int j = w[i] - 'a';
            if (node->children[j] && dfs(i + 1, node->children[j], diff)) {
                return true;
            }
            if (diff == 0) {
                for (int k = 0; k < 26; ++k) {
                    if (k != j && node->children[k]) {
                        if (dfs(i + 1, node->children[k], 1)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        };
        return dfs(0, this, 0);
    }
};

class MagicDictionary {
public:
    MagicDictionary() {
        trie = new Trie();
    }

    void buildDict(vector<string> dictionary) {
        for (auto& w : dictionary) {
            trie->insert(w);
        }
    }

    bool search(string searchWord) {
        return trie->search(searchWord);
    }

private:
    Trie* trie;
};

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary* obj = new MagicDictionary();
 * obj->buildDict(dictionary);
 * bool param_2 = obj->search(searchWord);
 */