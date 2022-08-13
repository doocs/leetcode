class Solution {
public:
    bool isInterleave(string s1, string s2, string s3) {
        int m = s1.size(), n = s2.size();
        if (m + n != s3.size()) return false;

        unordered_map<int, bool> memo;

        function<bool(int, int)> dfs;
        dfs = [&](int i, int j) {
            if (i == m && j == n) return true;
            auto it = memo.find(i * 100 + j);
            if (it != memo.end()) return it->second;

            bool ret = (i < m && s1[i] == s3[i + j] && dfs(i + 1, j)) || (j < n && s2[j] == s3[i + j] && dfs(i, j + 1));

            memo[i * 100 + j] = ret;
            return ret;
        };

        return dfs(0, 0);
    }
};
