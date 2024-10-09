class Solution {
    func isInterleave(_ s1: String, _ s2: String, _ s3: String) -> Bool {
        let m = s1.count, n = s2.count
        if m + n != s3.count {
            return false
        }
        
        let s1 = Array(s1), s2 = Array(s2), s3 = Array(s3)
        var dp = Array(repeating: Array(repeating: false, count: n + 1), count: m + 1)
        dp[0][0] = true
        
        for i in 0...m {
            for j in 0...n {
                let k = i + j - 1
                if i > 0 && s1[i - 1] == s3[k] {
                    dp[i][j] = dp[i][j] || dp[i - 1][j]
                }
                if j > 0 && s2[j - 1] == s3[k] {
                    dp[i][j] = dp[i][j] || dp[i][j - 1]
                }
            }
        }
        
        return dp[m][n]
    }
}