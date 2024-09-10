class Solution {
    func minimumTotal(_ triangle: [[Int]]) -> Int {
        let n = triangle.count
        var dp = Array(repeating: 0, count: n + 1)
        
        for i in (0..<n).reversed() {
            for j in 0...i {
                dp[j] = min(dp[j], dp[j + 1]) + triangle[i][j]
            }
        }
        
        return dp[0]
    }
}