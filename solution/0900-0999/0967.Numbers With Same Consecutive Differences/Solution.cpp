class Solution {
public:
    vector<int> ans;

    vector<int> numsSameConsecDiff(int n, int k) {
        for (int i = 1; i < 10; ++i)
            dfs(n - 1, k, i);
        return ans;
    }

    void dfs(int n, int k, int t) {
        if (n == 0) {
            ans.push_back(t);
            return;
        }
        int last = t % 10;
        if (last + k <= 9) dfs(n - 1, k, t * 10 + last + k);
        if (last - k >= 0 && k != 0) dfs(n - 1, k, t * 10 + last - k);
    }
};