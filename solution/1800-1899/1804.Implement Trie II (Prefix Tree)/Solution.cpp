class Trie {
public:
    Trie()
        : children(26)
        , v(0)
        , pv(0) {
    }

    void insert(string word) {
        Trie* node = this;
        for (char c : word) {
            c -= 'a';
            if (!node->children[c]) {
                node->children[c] = new Trie();
            }
            node = node->children[c];
            ++node->pv;
        }
        ++node->v;
    }

    int countWordsEqualTo(string word) {
        Trie* node = search(word);
        return node ? node->v : 0;
    }

    int countWordsStartingWith(string prefix) {
        Trie* node = search(prefix);
        return node ? node->pv : 0;
    }

    void erase(string word) {
        Trie* node = this;
        for (char c : word) {
            c -= 'a';
            node = node->children[c];
            --node->pv;
        }
        --node->v;
    }

private:
    vector<Trie*> children;
    int v, pv;

    Trie* search(string& word) {
        Trie* node = this;
        for (char c : word) {
            c -= 'a';
            if (!node->children[c]) {
                return nullptr;
            }
            node = node->children[c];
        }
        return node;
    }
};

/**
 * Your Trie object will be instantiated and called as such:
 * Trie* obj = new Trie();
 * obj->insert(word);
 * int param_2 = obj->countWordsEqualTo(word);
 * int param_3 = obj->countWordsStartingWith(prefix);
 * obj->erase(word);
 */