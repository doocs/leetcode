class Solution {
public:
    unordered_map<string, vector<int>> memo;

    int countEval(string s, int result) {
        vector<int> ans = dfs(s);
        return result == 0 || result == 1 ? ans[result] : 0;
    }

    vector<int> dfs(string s) {
        if (memo.count(s)) return memo[s];
        vector<int> res(2);
        if (s.size() == 1) {
            res[s[0] - '0'] = 1;
            return res;
        }
        for (int k = 0; k < s.size(); ++k) {
            if (s[k] == '0' || s[k] == '1') continue;
            vector<int> left = dfs(s.substr(0, k));
            vector<int> right = dfs(s.substr(k + 1, s.size() - k));
            for (int i = 0; i < 2; ++i) {
                for (int j = 0; j < 2; ++j) {
                    int v = 0;
                    if (s[k] == '&')
                        v = i & j;
                    else if (s[k] == '|')
                        v = i | j;
                    else if (s[k] == '^')
                        v = i ^ j;
                    res[v] += left[i] * right[j];
                }
            }
        }
        memo[s] = res;
        return res;
    }
};