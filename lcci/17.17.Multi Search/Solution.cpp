class Trie {
private:
    vector<Trie*> children;
    int idx;

public:
    Trie()
        : children(26)
        , idx(-1) {}

    void insert(string word, int i) {
        Trie* node = this;
        for (char c : word) {
            int idx = c - 'a';
            if (!node->children[idx]) node->children[idx] = new Trie();
            node = node->children[idx];
        }
        node->idx = i;
    }

    vector<int> search(string word) {
        Trie* node = this;
        vector<int> res;
        for (char c : word) {
            int idx = c - 'a';
            if (!node->children[idx]) return res;
            node = node->children[idx];
            if (node->idx != -1) res.push_back(node->idx);
        }
        return res;
    }
};

class Solution {
public:
    vector<vector<int>> multiSearch(string big, vector<string>& smalls) {
        Trie* tree = new Trie();
        int n = smalls.size();
        for (int i = 0; i < n; ++i) tree->insert(smalls[i], i);
        vector<vector<int>> ans(n);
        for (int i = 0, m = big.size(); i < m; ++i) {
            string s = big.substr(i, m - i);
            vector<int> t = tree->search(s);
            for (int& idx : t) ans[idx].push_back(i);
        }
        return ans;
    }
};