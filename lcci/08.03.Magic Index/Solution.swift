class Solution {
    func findMagicIndex(_ nums: [Int]) -> Int {
        let left = 0
        let right = nums.count - 1
        return find(nums, left, right)
    }

    private func find(_ nums: [Int], _ left: Int, _ right: Int) -> Int {
        if left > right {
            return -1
        }
        let mid = (left + right) >> 1
        let leftIndex = find(nums, left, mid - 1)
        if leftIndex != -1 {
            return leftIndex
        }
        if nums[mid] == mid {
            return mid
        }
        return find(nums, mid + 1, right)
    }
}
