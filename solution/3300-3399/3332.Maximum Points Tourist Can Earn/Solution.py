class Solution:
    def maxScore(
        self, n: int, k: int, stayScore: List[List[int]], travelScore: List[List[int]]
    ) -> int:
        f = [[-inf] * n for _ in range(k + 1)]
        f[0] = [0] * n
        for i in range(1, k + 1):
            for j in range(n):
                for h in range(n):
                    f[i][j] = max(
                        f[i][j],
                        f[i - 1][h]
                        + (stayScore[i - 1][j] if j == h else travelScore[h][j]),
                    )
        return max(f[k])
