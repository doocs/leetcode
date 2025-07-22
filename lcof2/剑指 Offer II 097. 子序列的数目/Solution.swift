class Solution {
    func numDistinct(_ s: String, _ t: String) -> Int {
        let m = s.count, n = t.count
        let sArray = Array(s)
        let tArray = Array(t)
        
        var dp = Array(repeating: Array(repeating: 0, count: n + 1), count: m + 1)
        
        
        for i in 0...m {
            dp[i][0] = 1
        }
        
        for i in 1...m {
            for j in 1...n {
                dp[i][j] = dp[i - 1][j]
                if sArray[i - 1] == tArray[j - 1] {
                    dp[i][j] += dp[i - 1][j - 1]
                }
            }
        }
        
        return dp[m][n]
    }
}