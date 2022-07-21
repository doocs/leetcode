class Solution:
    def lenLongestFibSubseq(self, arr: List[int]) -> int:
        mp = {v: i for i, v in enumerate(arr)}
        n = len(arr)
        dp = [[0] * n for _ in range(n)]
        for i in range(n):
            for j in range(i):
                dp[j][i] = 2
        ans = 0
        for i in range(n):
            for j in range(i):
                d = arr[i] - arr[j]
                if d in mp and (k := mp[d]) < j:
                    dp[j][i] = max(dp[j][i], dp[k][j] + 1)
                    ans = max(ans, dp[j][i])
        return ans
