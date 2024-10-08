class Solution {
    func canPartition(_ nums: [Int]) -> Bool {
        let s = nums.reduce(0, +)
        if s % 2 != 0 { return false }
        let target = s / 2
        var dp = Array(repeating: false, count: target + 1)
        dp[0] = true
        
        for num in nums {
            for j in stride(from: target, through: num, by: -1) {
                dp[j] = dp[j] || dp[j - num]
            }
        }
        
        return dp[target]
    }
}