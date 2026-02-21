class Solution:
    def almostPalindromic(self, s: str) -> int:
        def f(l: int, r: int) -> int:
            while l >= 0 and r < n and s[l] == s[r]:
                l -= 1
                r += 1
            l1, r1 = l - 1, r
            l2, r2 = l, r + 1
            while l1 >= 0 and r1 < n and s[l1] == s[r1]:
                l1 -= 1
                r1 += 1
            while l2 >= 0 and r2 < n and s[l2] == s[r2]:
                l2 -= 1
                r2 += 1
            return min(n, max(r1 - l1 - 1, r2 - l2 - 1))

        n = len(s)
        ans = 0
        for i in range(n):
            a = f(i, i)
            b = f(i, i + 1)
            ans = max(ans, a, b)
        return ans
