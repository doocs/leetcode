class Solution {
    private var ans = [[Int]]()
    private var g: [[Int]] = []
    private var m: Int = 0
    private var n: Int = 0

    func pathWithObstacles(_ obstacleGrid: [[Int]]) -> [[Int]] {
        g = obstacleGrid
        m = g.count
        n = g[0].count
        return dfs(0, 0) ? ans : []
    }

    private func dfs(_ i: Int, _ j: Int) -> Bool {
        if i >= m || j >= n || g[i][j] == 1 {
            return false
        }
        ans.append([i, j])
        g[i][j] = 1
        if (i == m - 1 && j == n - 1) || dfs(i + 1, j) || dfs(i, j + 1) {
            return true
        }
        ans.removeLast()
        return false
    }
}
