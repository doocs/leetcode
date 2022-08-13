class Solution {
public:
    const int inf = INT_MAX;
    int n;

    int minimumCost(string sentence, int k) {
        istringstream is(sentence);
        vector<string> words;
        string word;
        while (is >> word) words.push_back(word);
        n = words.size();
        vector<int> s(n + 1);
        for (int i = 0; i < n; ++i) s[i + 1] = s[i] + words[i].size();
        vector<int> memo(n, inf);
        return dfs(0, k, s, memo);
    }

    int dfs(int i, int k, vector<int>& s, vector<int>& memo) {
        if (memo[i] != inf) return memo[i];
        if (s[n] - s[i] + n - i - 1 <= k) {
            memo[i] = 0;
            return 0;
        }
        int ans = inf;
        for (int j = i + 1; j < n; ++j) {
            int t = s[j] - s[i] + j - i - 1;
            if (t <= k) ans = min(ans, (k - t) * (k - t) + dfs(j, k, s, memo));
        }
        memo[i] = ans;
        return ans;
    }
};