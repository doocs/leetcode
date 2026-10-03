class Solution:
    def maxCoins(self, lane1: List[int], lane2: List[int]) -> int:
        n = len(lane1)
        lanes = (lane1, lane2)
        f = [[[0] * 3 for _ in range(2)] for _ in range(n + 1)]
        for i in range(n - 1, -1, -1):
            for k in range(3):
                for j in range(2):
                    x = lanes[j][i]
                    ans = max(x, f[i + 1][j][k] + x)
                    if k:
                        ans = max(ans, f[i + 1][j ^ 1][k - 1] + x, f[i][j ^ 1][k - 1])
                    f[i][j][k] = ans
        return max(f[i][0][2] for i in range(n))
