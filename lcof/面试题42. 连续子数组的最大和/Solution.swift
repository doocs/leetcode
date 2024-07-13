class Solution {
    func maxSubArray(_ nums: [Int]) -> Int {
        var ans = Int.min
        var currentSum = 0
        
        for x in nums {
            currentSum = max(currentSum, 0) + x
            ans = max(ans, currentSum)
        }
        
        return ans
    }
}