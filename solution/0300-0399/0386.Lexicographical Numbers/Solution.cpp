class Solution {
public:
    vector<int> lexicalOrder(int n) {
        vector<int> ans;
        for (int i = 1; i < 10; ++i) dfs(i, n, ans);
        return ans;
    }

    void dfs(int u, int n, vector<int>& ans) {
        if (u > n) return;
        ans.push_back(u);
        for (int i = 0; i < 10; ++i) dfs(u * 10 + i, n, ans);
    }
};