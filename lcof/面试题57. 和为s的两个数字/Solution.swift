class Solution {
    func twoSum(_ nums: [Int], _ target: Int) -> [Int] {
        var l = 0
        var r = nums.count - 1
        
        while l < r {
            let sum = nums[l] + nums[r]
            if sum == target {
                return [nums[l], nums[r]]
            } else if sum > target {
                r -= 1
            } else {
                l += 1
            }
        }
        
        return []
    }
}
