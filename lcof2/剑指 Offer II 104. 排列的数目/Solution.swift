class Solution {
    func combinationSum4(_ nums: [Int], _ target: Int) -> Int {
        var dp = [Int](repeating: 0, count: target + 1)
        dp[0] = 1
        
        for i in 1...target {
            for num in nums {
                if i >= num, dp[i] <= Int.max - dp[i - num] {
                    dp[i] += dp[i - num]
                }
            }
        }

        return dp[target]
    }
}
