class Trie {
private:
    Trie* children[2];

public:
    Trie()
        : children{nullptr, nullptr} {}

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
        Trie* node = this;
        int ans = 0;
        for (int i = 30; ~i; --i) {
            int v = (x >> i) & 1;
            if (node->children[v ^ 1]) {
                ans |= 1 << i;
                node = node->children[v ^ 1];
            } else if (node->children[v]) {
                node = node->children[v];
            } else {
                return -1;
            }
        }
        return ans;
    }
};

class Solution {
public:
    vector<int> maximizeXor(vector<int>& nums, vector<vector<int>>& queries) {
        sort(nums.begin(), nums.end());
        int n = queries.size();
        vector<int> idx(n);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) { return queries[i][1] < queries[j][1]; });
        vector<int> ans(n);
        Trie trie;
        int j = 0;
        for (int i : idx) {
            int x = queries[i][0], m = queries[i][1];
            while (j < nums.size() && nums[j] <= m) {
                trie.insert(nums[j++]);
            }
            ans[i] = trie.search(x);
        }
        return ans;
    }
};