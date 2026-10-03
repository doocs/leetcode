class Solution {
    func searchInsert(_ nums: [Int], _ target: Int) -> Int {
        var left = 0
        var right = nums.count
        while left < right {
            let mid = (left + right) / 2
            if nums[mid] >= target {
                right = mid
            } else {
                left = mid + 1
            }
        }
        return left
    }
}
