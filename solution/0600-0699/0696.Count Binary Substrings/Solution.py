class Solution:
    def countBinarySubstrings(self, s: str) -> int:
        n = len(s)
        ans = i = 0
        pre = 0
        while i < n:
            j = i + 1
            while j < n and s[j] == s[i]:
                j += 1
            cur = j - i
            ans += min(pre, cur)
            pre = cur
            i = j
        return ans
