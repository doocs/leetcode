class Solution:
    def maxPathScore(self, grid: List[List[int]], k: int) -> int:
        @cache
        def dfs(i: int, j: int, k: int) -> int:
            if i < 0 or j < 0 or k < 0:
                return -inf
            if i == 0 and j == 0:
                return 0
            res = grid[i][j]
            if grid[i][j]:
                k -= 1
            a = dfs(i - 1, j, k)
            b = dfs(i, j - 1, k)
            res += max(a, b)
            return res

        ans = dfs(len(grid) - 1, len(grid[0]) - 1, k)
        dfs.cache_clear()
        return -1 if ans < 0 else ans
