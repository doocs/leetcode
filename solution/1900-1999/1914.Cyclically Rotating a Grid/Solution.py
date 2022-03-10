class Solution:
    def rotateGrid(self, grid: List[List[int]], k: int) -> List[List[int]]:
        def rotate(grid, s1, e1, s2, e2, k):
            t = []
            for j in range(e2, e1, -1):
                t.append(grid[s1][j])
            for i in range(s1, s2):
                t.append(grid[i][e1])
            for j in range(e1, e2):
                t.append(grid[s2][j])
            for i in range(s2, s1, -1):
                t.append(grid[i][e2])
            k %= len(t)
            t = t[-k:] + t[:-k]
            k = 0
            for j in range(e2, e1, -1):
                grid[s1][j] = t[k]
                k += 1
            for i in range(s1, s2):
                grid[i][e1] = t[k]
                k += 1
            for j in range(e1, e2):
                grid[s2][j] = t[k]
                k += 1
            for i in range(s2, s1, -1):
                grid[i][e2] = t[k]
                k += 1

        m, n = len(grid), len(grid[0])
        s1 = e1 = 0
        s2, e2 = m - 1, n - 1
        while s1 <= s2 and e1 <= e2:
            rotate(grid, s1, e1, s2, e2, k)
            s1 += 1
            e1 += 1
            s2 -= 1
            e2 -= 1
        return grid
