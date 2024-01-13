class Solution:
    def countRoutes(
        self, locations: List[int], start: int, finish: int, fuel: int
    ) -> int:
        mod = 10**9 + 7
        n = len(locations)
        f = [[0] * (fuel + 1) for _ in range(n)]
        for k in range(fuel + 1):
            f[finish][k] = 1
        for k in range(fuel + 1):
            for i in range(n):
                for j in range(n):
                    if j != i and abs(locations[i] - locations[j]) <= k:
                        f[i][k] = (
                            f[i][k] + f[j][k - abs(locations[i] - locations[j])]
                        ) % mod
        return f[start][fuel]
