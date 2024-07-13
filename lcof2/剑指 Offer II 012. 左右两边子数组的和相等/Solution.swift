class Solution {
    func pivotIndex(_ nums: [Int]) -> Int {
        var leftSum = 0
        var rightSum = nums.reduce(0, +)
        
        for i in 0..<nums.count {
            rightSum -= nums[i]
            if leftSum == rightSum {
                return i
            }
            leftSum += nums[i]
        }
        return -1
    }
}