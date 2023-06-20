class Solution:
    def pondSizes(self, land: List[List[int]]) -> List[int]:
        def dfs(i: int, j: int) -> int:
            res = 1
            land[i][j] = 1
            for x in range(i - 1, i + 2):
                for y in range(j - 1, j + 2):
                    if 0 <= x < m and 0 <= y < n and land[x][y] == 0:
                        res += dfs(x, y)
            return res

        m, n = len(land), len(land[0])
        return sorted(dfs(i, j) for i in range(m) for j in range(n) if land[i][j] == 0)
