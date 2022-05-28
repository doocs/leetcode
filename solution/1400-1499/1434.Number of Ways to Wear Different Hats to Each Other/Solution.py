class Solution:
    def numberWays(self, hats: List[List[int]]) -> int:
        d = defaultdict(list)
        for i, h in enumerate(hats):
            for v in h:
                d[v].append(i)
        n = len(hats)
        mx = max(max(h) for h in hats)
        dp = [[0] * (1 << n) for _ in range(mx + 1)]
        dp[0][0] = 1
        mod = int(1e9) + 7
        for i in range(1, mx + 1):
            for mask in range(1 << n):
                dp[i][mask] = dp[i - 1][mask]
                for j in d[i]:
                    if (mask >> j) & 1:
                        dp[i][mask] += dp[i - 1][mask ^ (1 << j)]
                dp[i][mask] %= mod
        return dp[mx][(1 << n) - 1]
