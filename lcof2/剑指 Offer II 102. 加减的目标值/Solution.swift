class Solution {
    func findTargetSumWays(_ nums: [Int], _ target: Int) -> Int {
        if target < -1000 || target > 1000 {
            return 0
        }
        
        let n = nums.count
        var dp = Array(repeating: Array(repeating: 0, count: 2001), count: n)
        
        dp[0][nums[0] + 1000] += 1
        dp[0][-nums[0] + 1000] += 1
        
        for i in 1..<n {
            for j in -1000...1000 {
                if dp[i - 1][j + 1000] > 0 {
                    dp[i][j + nums[i] + 1000] += dp[i - 1][j + 1000]
                    dp[i][j - nums[i] + 1000] += dp[i - 1][j + 1000]
                }
            }
        }
        
        return dp[n - 1][target + 1000]
    }
}