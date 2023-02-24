class Trie {
public:
    void insert(string& w, int i) {
        Trie* node = this;
        for (int j = 0; j < w.size(); ++j) {
            int idx = w[j] - 'a';
            if (!node->children[idx]) {
                node->children[idx] = new Trie();
            }
            node = node->children[idx];
            if (node->v.size() < 3) {
                node->v.push_back(i);
            }
        }
    }

    vector<vector<int>> search(string& w) {
        Trie* node = this;
        int n = w.size();
        vector<vector<int>> ans(n);
        for (int i = 0; i < w.size(); ++i) {
            int idx = w[i] - 'a';
            if (!node->children[idx]) {
                break;
            }
            node = node->children[idx];
            ans[i] = move(node->v);
        }
        return ans;
    }

private:
    vector<Trie*> children = vector<Trie*>(26);
    vector<int> v;
};

class Solution {
public:
    vector<vector<string>> suggestedProducts(vector<string>& products, string searchWord) {
        sort(products.begin(), products.end());
        Trie* trie = new Trie();
        for (int i = 0; i < products.size(); ++i) {
            trie->insert(products[i], i);
        }
        vector<vector<string>> ans;
        for (auto& v : trie->search(searchWord)) {
            vector<string> t;
            for (int i : v) {
                t.push_back(products[i]);
            }
            ans.push_back(move(t));
        }
        return ans;
    }
};