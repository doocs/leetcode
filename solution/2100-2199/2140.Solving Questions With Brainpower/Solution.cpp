class Solution {
public:
    long long mostPoints(vector<vector<int>>& questions) {
        vector<long long> memo(questions.size(), -1);
        return dfs(0, questions, memo);
    }

    long long dfs(int i, vector<vector<int>>& questions, vector<long long>& memo) {
        if (i >= questions.size()) return 0;
        if (memo[i] != -1) return memo[i];
        long long ans = max(questions[i][0] + dfs(i + questions[i][1] + 1, questions, memo), dfs(i + 1, questions, memo));
        memo[i] = ans;
        return ans;
    }
};