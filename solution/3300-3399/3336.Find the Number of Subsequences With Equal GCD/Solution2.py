class Solution:
    def subsequencePairCount(self, nums: List[int]) -> int:
        mod = 10**9 + 7
        m = max(nums)
        f = [[0] * (m + 1) for _ in range(m + 1)]
        f[0][0] = 1
        for x in nums:
            g = [[0] * (m + 1) for _ in range(m + 1)]
            for j in range(m + 1):
                for k in range(m + 1):
                    if f[j][k] == 0:
                        continue
                    v = f[j][k]
                    g[j][k] = (g[j][k] + v) % mod
                    g[gcd(x, j)][k] = (g[gcd(x, j)][k] + v) % mod
                    g[j][gcd(x, k)] = (g[j][gcd(x, k)] + v) % mod
            f = g
        return (sum(f[i][i] for i in range(m + 1)) - 1) % mod
