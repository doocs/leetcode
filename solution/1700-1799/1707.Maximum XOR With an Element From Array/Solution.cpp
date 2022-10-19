class Trie {
public:
    Trie()
        : children(2) {}
    void insert(int x) {
        Trie* node = this;
        for (int i = 30; ~i; --i) {
            int v = (x >> i) & 1;
            if (!node->children[v]) {
                node->children[v] = new Trie();
            }
            node = node->children[v];
        }
    }

    int search(int x) {
        int ans = 0;
        Trie* node = this;
        for (int i = 30; ~i; --i) {
            int v = (x >> i) & 1;
            if (node->children[v ^ 1]) {
                node = node->children[v ^ 1];
                ans |= 1 << i;
            } else if (node->children[v]) {
                node = node->children[v];
            } else {
                return -1;
            }
        }
        return ans;
    }

private:
    vector<Trie*> children;
};

class Solution {
public:
    vector<int> maximizeXor(vector<int>& nums, vector<vector<int>>& queries) {
        sort(nums.begin(), nums.end());
        int n = queries.size();
        vector<tuple<int, int, int>> qs;
        for (int i = 0; i < n; ++i) {
            qs.push_back({queries[i][1], queries[i][0], i});
        }
        sort(qs.begin(), qs.end());
        Trie* trie = new Trie();
        int j = 0;
        vector<int> ans(n);
        for (auto& [m, x, i] : qs) {
            while (j < nums.size() && nums[j] <= m) {
                trie->insert(nums[j++]);
            }
            ans[i] = trie->search(x);
        }
        return ans;
    }
};