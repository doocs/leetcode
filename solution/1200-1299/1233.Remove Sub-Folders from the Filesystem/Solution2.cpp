class Trie {
public:
    void insert(int fid, string& f) {
        Trie* node = this;
        vector<string> ps = split(f, '/');
        for (int i = 1; i < ps.size(); ++i) {
            auto& p = ps[i];
            if (!node->children.count(p)) {
                node->children[p] = new Trie();
            }
            node = node->children[p];
        }
        node->fid = fid;
    }

    vector<int> search() {
        vector<int> ans;
        function<void(Trie*)> dfs = [&](Trie* root) {
            if (root->fid != -1) {
                ans.push_back(root->fid);
                return;
            }
            for (auto& [_, child] : root->children) {
                dfs(child);
            }
        };
        dfs(this);
        return ans;
    }

    vector<string> split(string& s, char delim) {
        stringstream ss(s);
        string item;
        vector<string> res;
        while (getline(ss, item, delim)) {
            res.emplace_back(item);
        }
        return res;
    }

private:
    unordered_map<string, Trie*> children;
    int fid = -1;
};

class Solution {
public:
    vector<string> removeSubfolders(vector<string>& folder) {
        Trie* trie = new Trie();
        for (int i = 0; i < folder.size(); ++i) {
            trie->insert(i, folder[i]);
        }
        vector<string> ans;
        for (int i : trie->search()) {
            ans.emplace_back(folder[i]);
        }
        return ans;
    }
};