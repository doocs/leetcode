class Solution:
    def countSubstrings(self, s: str, t: str) -> int:
        m, n = len(s), len(t)
        ans = 0
        for i in range(m):
            for j in range(n):
                if s[i] != t[j]:
                    l = r = 1
                    while i - l >= 0 and j - l >= 0 and s[i - l] == t[j - l]:
                        l += 1
                    while i + r < m and j + r < n and s[i + r] == t[j + r]:
                        r += 1
                    ans += l * r
        return ans
