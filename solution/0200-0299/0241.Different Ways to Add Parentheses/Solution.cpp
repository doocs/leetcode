class Solution {
public:
    vector<int> diffWaysToCompute(string expression) {
        return dfs(expression);
    }

    vector<int> dfs(string exp) {
        if (memo.count(exp)) return memo[exp];
        if (exp.size() < 3) return {stoi(exp)};
        vector<int> ans;
        int n = exp.size();
        for (int i = 0; i < n; ++i) {
            char c = exp[i];
            if (c == '-' || c == '+' || c == '*') {
                vector<int> left = dfs(exp.substr(0, i));
                vector<int> right = dfs(exp.substr(i + 1, n - i - 1));
                for (int& a : left) {
                    for (int& b : right) {
                        if (c == '-')
                            ans.push_back(a - b);
                        else if (c == '+')
                            ans.push_back(a + b);
                        else
                            ans.push_back(a * b);
                    }
                }
            }
        }
        memo[exp] = ans;
        return ans;
    }

private:
    unordered_map<string, vector<int>> memo;
};