class Solution {
public:
    int palindromePartition(string s, int k) {
        int len = s.length();
        // cost[i][j] = min #changes needed to turn (s[i],...,s[i+j]) into a palindrome
        vector<vector<int>> cost(len, vector<int>(len));
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                int begin = i, end = j, cnt = 0;
                while (begin < end) {
                    if (s[begin] != s[end])
                        cnt++;
                    begin++, end--;
                }
                cost[i][j - i] = cnt;
            }
        }
        // dp[i][j] = min #changes needed to split (s[i],...,s[len]) into j+1 palindromes
        vector<vector<int>> dp(len, vector<int>(k, INT_MAX));
        for (int i = 0; i < len; i++) {
            dp[i][0] = cost[i][len - 1 - i];
        }
        for (int kk = 1; kk < k; kk++) {
            for (int i = 0; i < len; i++) {
                for (int j = i + 1; j + kk - 1 < len; j++) {
                    dp[i][kk] = min(dp[i][kk], dp[j][kk - 1] + cost[i][j - i - 1]);
                }
            }
        }
        return dp[0][k - 1];
    }
};