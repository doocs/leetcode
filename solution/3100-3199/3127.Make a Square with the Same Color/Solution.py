class Solution:
    def canMakeSquare(self, grid: List[List[str]]) -> bool:
        for i in range(0, 2):
            for j in range(0, 2):
                cnt1 = cnt2 = 0
                for a, b in pairwise((0, 0, 1, 1, 0)):
                    x, y = i + a, j + b
                    cnt1 += grid[x][y] == "W"
                    cnt2 += grid[x][y] == "B"
                if cnt1 != cnt2:
                    return True
        return False
