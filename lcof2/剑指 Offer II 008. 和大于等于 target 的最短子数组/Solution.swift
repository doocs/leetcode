class Solution {
    func minSubArrayLen(_ target: Int, _ nums: [Int]) -> Int {
        let inf = Int.max
        var ans = inf
        var sum = 0
        var i = 0
        
        for j in 0..<nums.count {
            sum += nums[j]
            while sum >= target {
                ans = min(ans, j - i + 1)
                sum -= nums[i]
                i += 1
            }
        }
        
        return ans == inf ? 0 : ans
    }
}