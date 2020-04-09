class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        if not s:
            return 0
        cache = {}
        cache[s[0]] = 0
        dp = [0 for _ in s]
        dp[0] = res = 1
        for i in range(1, len(s)):
            if s[i] == s[i - 1]:
                dp[i] = 1
            else:
                if cache.get(s[i]) is None:
                    dp[i] = dp[i - 1] + 1
                else:
                    dp[i] = min(dp[i - 1] + 1, i - cache[s[i]])
            cache[s[i]] = i
            res = max(res, dp[i])
        return res