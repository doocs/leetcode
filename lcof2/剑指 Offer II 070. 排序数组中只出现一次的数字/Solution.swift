class Solution {
    func singleNonDuplicate(_ nums: [Int]) -> Int {
        var left = 0
        var right = nums.count - 1
        
        while left < right {
            let mid = (left + right) / 2
            if nums[mid] != nums[mid ^ 1] {
                right = mid
            } else {
                left = mid + 1
            }
        }
        
        return nums[left]
    }
}