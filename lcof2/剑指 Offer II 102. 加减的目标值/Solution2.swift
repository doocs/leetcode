class Solution {
    func findTargetSumWays(_ nums: [Int], _ target: Int) -> Int {
        let s = nums.reduce(0, +)
        if s - target < 0 || (s - target) % 2 != 0 {
            return 0
        }
        let target = (s - target) / 2
        var dp = [Int](repeating: 0, count: target + 1)
        dp[0] = 1
        
        for num in nums {
            for j in stride(from: target, through: num, by: -1) {
                dp[j] += dp[j - num]
            }
        }
        return dp[target]
    }
}