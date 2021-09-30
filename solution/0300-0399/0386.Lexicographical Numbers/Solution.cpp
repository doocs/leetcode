class Solution {
public:
    vector<int> lexicalOrder(int n) {
        vector<int> res;
        for (int i = 1; i < 10; ++i)
        {
            dfs(res, i, n);
        }
        return res;
    }

    void dfs(vector<int> &res, int i, int n) {
        if (i > n)
            return;
        res.push_back(i);
        for (int j = 0; j < 10; ++j)
        {
            dfs(res, i * 10 + j, n);
        }
    }
};