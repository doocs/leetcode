class Trie {
private:
    const int inf = 1 << 30;
    Trie* children[26];
    int length = inf;
    int idx = inf;

public:
    Trie() {
        for (int i = 0; i < 26; ++i) {
            children[i] = nullptr;
        }
    }

    void insert(string w, int i) {
        Trie* node = this;
        if (node->length > w.length()) {
            node->length = w.length();
            node->idx = i;
        }
        for (int k = w.length() - 1; k >= 0; --k) {
            int idx = w[k] - 'a';
            if (node->children[idx] == nullptr) {
                node->children[idx] = new Trie();
            }
            node = node->children[idx];
            if (node->length > w.length()) {
                node->length = w.length();
                node->idx = i;
            }
        }
    }

    int query(string w) {
        Trie* node = this;
        for (int k = w.length() - 1; k >= 0; --k) {
            int idx = w[k] - 'a';
            if (node->children[idx] == nullptr) {
                break;
            }
            node = node->children[idx];
        }
        return node->idx;
    }
};

class Solution {
public:
    vector<int> stringIndices(vector<string>& wordsContainer, vector<string>& wordsQuery) {
        Trie* trie = new Trie();
        for (int i = 0; i < wordsContainer.size(); ++i) {
            trie->insert(wordsContainer[i], i);
        }
        int n = wordsQuery.size();
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            ans[i] = trie->query(wordsQuery[i]);
        }
        return ans;
    }
};