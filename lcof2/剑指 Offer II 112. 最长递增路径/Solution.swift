class Solution {
    private var memo: [[Int]] = []
    private var matrix: [[Int]] = []
    private var m: Int = 0
    private var n: Int = 0

    func longestIncreasingPath(_ matrix: [[Int]]) -> Int {
        self.matrix = matrix
        m = matrix.count
        n = matrix[0].count
        memo = Array(repeating: Array(repeating: -1, count: n), count: m)
        
        var ans = 0
        for i in 0..<m {
            for j in 0..<n {
                ans = max(ans, dfs(i, j))
            }
        }
        return ans
    }

    private func dfs(_ i: Int, _ j: Int) -> Int {
        if memo[i][j] != -1 {
            return memo[i][j]
        }
        var ans = 1
        let dirs = [(-1, 0), (1, 0), (0, -1), (0, 1)]
        
        for (dx, dy) in dirs {
            let x = i + dx, y = j + dy
            if x >= 0, x < m, y >= 0, y < n, matrix[x][y] > matrix[i][j] {
                ans = max(ans, dfs(x, y) + 1)
            }
        }
        memo[i][j] = ans
        return ans
    }
}
