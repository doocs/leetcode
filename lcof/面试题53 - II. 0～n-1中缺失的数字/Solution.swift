class Solution {
    func missingNumber(_ nums: [Int]) -> Int {
        var left = 0
        var right = nums.count
        
        while left < right {
            let mid = (left + right) / 2
            if nums[mid] > mid {
                right = mid
            } else {
                left = mid + 1
            }
        }
        return left
    }
}