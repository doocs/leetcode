class Trie {
public:
    Trie()
        : children(26)
        , cnt(0) {}

    void insert(string w) {
        Trie* node = this;
        for (auto& c : w) {
            int i = c - 'a';
            if (!node->children[i]) {
                node->children[i] = new Trie();
            }
            node = node->children[i];
            ++node->cnt;
        }
    }

    int search(string pref) {
        Trie* node = this;
        for (auto& c : pref) {
            int i = c - 'a';
            if (!node->children[i]) {
                return 0;
            }
            node = node->children[i];
        }
        return node->cnt;
    }

private:
    vector<Trie*> children;
    int cnt;
};

class Solution {
public:
    int prefixCount(vector<string>& words, string pref) {
        Trie* tree = new Trie();
        for (auto& w : words) {
            tree->insert(w);
        }
        return tree->search(pref);
    }
};