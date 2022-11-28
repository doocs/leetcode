class Solution:
    def countHomogenous(self, s: str) -> int:
        mod = 10**9 + 7
        i, n = 0, len(s)
        ans = 0
        while i < n:
            j = i
            while j < n and s[j] == s[i]:
                j += 1
            cnt = j - i
            ans += (1 + cnt) * cnt // 2
            ans %= mod
            i = j
        return ans
