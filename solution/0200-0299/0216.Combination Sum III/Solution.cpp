class Solution {
public:
    vector<vector<int>> ans;

    vector<vector<int>> combinationSum3(int k, int n) {
        vector<int> t;
        dfs(0, n, k, t);
        return ans;
    }

    void dfs(int i, int n, int k, vector<int>& t) {
        if (i > 9 || n < 0 || t.size() > k) return;
        if (n == 0 && t.size() == k) {
            ans.push_back(t);
            return;
        }
        ++i;
        t.push_back(i);
        dfs(i, n - i, k, t);
        t.pop_back();
        dfs(i, n, k, t);
    }
};