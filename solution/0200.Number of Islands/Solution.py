class Solution:
    def numIslands(self, grid):
        """
        :type grid: List[List[str]]
        :rtype: int
        """
        def dp(x, y):
            if x >= 0 and x < len(grid) and y >= 0 and y < len(grid[x]) and grid[x][y] == '1':
                grid[x][y] = '0'
                dp(x-1, y)
                dp(x+1, y)
                dp(x, y-1)
                dp(x, y+1)
        ans = 0
        for i in range(len(grid)):
            for j in range(len(grid[i])):
                if grid[i][j] == '1':
                    ans += 1
                    dp(i, j)
        return ans
