class Solution:
    def sumOfGoodSubsequences(self, nums: List[int]) -> int:
        mod = 10**9 + 7
        f = defaultdict(int)
        g = defaultdict(int)
        for x in nums:
            f[x] += x
            g[x] += 1
            f[x] += f[x - 1] + g[x - 1] * x
            g[x] += g[x - 1]
            f[x] += f[x + 1] + g[x + 1] * x
            g[x] += g[x + 1]
        return sum(f.values()) % mod
