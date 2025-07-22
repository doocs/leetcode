class Solution {
    private var m = 0
    private var n = 0
    private var grid: [[Int]] = []

    func maxAreaOfIsland(_ grid: [[Int]]) -> Int {
        self.m = grid.count
        self.n = grid[0].count
        self.grid = grid
        var maxArea = 0

        for i in 0..<m {
            for j in 0..<n {
                maxArea = max(maxArea, dfs(i, j))
            }
        }

        return maxArea
    }

    private func dfs(_ i: Int, _ j: Int) -> Int {
        if grid[i][j] == 0 {
            return 0
        }

        var area = 1
        grid[i][j] = 0
        let dirs = [-1, 0, 1, 0, -1]
        
        for k in 0..<4 {
            let x = i + dirs[k], y = j + dirs[k + 1]
            if x >= 0 && x < m && y >= 0 && y < n {
                area += dfs(x, y)
            }
        }

        return area
    }
}