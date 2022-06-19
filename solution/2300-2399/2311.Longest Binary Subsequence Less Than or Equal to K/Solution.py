class Solution:
    def longestSubsequence(self, s: str, k: int) -> int:
        n = len(s)
        ans = s.count('0')
        v = 0
        for i in range(n - 1, -1, -1):
            if s[i] == '1':
                if v + (1 << (n - i - 1)) > k:
                    break
                ans += 1
                v += 1 << (n - i - 1)
        return ans
