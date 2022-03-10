class Solution:
    def findFarmland(self, land: List[List[int]]) -> List[List[int]]:
        m, n = len(land), len(land[0])
        ans = []
        for i in range(m):
            for j in range(n):
                if (
                    land[i][j] == 0
                    or (j > 0 and land[i][j - 1] == 1)
                    or (i > 0 and land[i - 1][j] == 1)
                ):
                    continue
                x, y = i, j
                while x + 1 < m and land[x + 1][j] == 1:
                    x += 1
                while y + 1 < n and land[x][y + 1] == 1:
                    y += 1
                ans.append([i, j, x, y])
        return ans
