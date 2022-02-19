class Solution {
public:
    vector<int> fraction(vector<int>& cont) {
        return dfs(cont, 0);
    }

    vector<int> dfs(vector<int>& cont, int i) {
        if (i == cont.size() - 1) return {cont[i], 1};
        vector<int> ans = dfs(cont, i + 1);
        int a = ans[0], b = ans[1];
        return {a * cont[i] + b, a};
    }
};