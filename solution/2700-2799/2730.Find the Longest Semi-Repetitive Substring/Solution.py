class Solution:
    def longestSemiRepetitiveSubstring(self, s: str) -> int:
        n = len(s)
        ans = cnt = j = 0
        for i in range(n):
            if i and s[i] == s[i - 1]:
                cnt += 1
            while cnt > 1:
                if s[j] == s[j + 1]:
                    cnt -= 1
                j += 1
            ans = max(ans, i - j + 1)
        return ans
