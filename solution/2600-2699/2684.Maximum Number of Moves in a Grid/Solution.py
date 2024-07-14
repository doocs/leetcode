class Solution:
    def maxMoves(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        q = set(range(m))
        for j in range(n - 1):
            t = set()
            for i in q:
                for k in range(i - 1, i + 2):
                    if 0 <= k < m and grid[i][j] < grid[k][j + 1]:
                        t.add(k)
            if not t:
                return j
            q = t
        return n - 1
