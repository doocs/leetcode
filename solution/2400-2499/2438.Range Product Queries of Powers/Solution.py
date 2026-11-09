class Solution:
    def productQueries(self, n: int, queries: list[list[int]]) -> list[int]:
        powers = []
        while n:
            x = n & -n
            powers.append(x)
            n -= x
        mod = 10**9 + 7
        ans = []
        for l, r in queries:
            x = 1
            for i in range(l, r + 1):
                x = x * powers[i] % mod
            ans.append(x)
        return ans
