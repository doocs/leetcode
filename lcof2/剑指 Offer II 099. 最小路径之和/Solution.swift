class Solution {
    func minPathSum(_ grid: [[Int]]) -> Int {
        let m = grid.count
        let n = grid[0].count
        var dp = grid

        for i in 1..<m {
            dp[i][0] += dp[i-1][0]
        }
        
        for j in 1..<n {
            dp[0][j] += dp[0][j-1]
        }
        
        for i in 1..<m {
            for j in 1..<n {
                dp[i][j] += min(dp[i-1][j], dp[i][j-1])
            }
        }
        
        return dp[m-1][n-1]
    }
}
