class Solution {
    func findMagicIndex(_ nums: [Int]) -> Int {
        return find(nums, 0, nums.count - 1)
    }

    private func find(_ nums: [Int], _ i: Int, _ j: Int) -> Int {
        if i > j {
            return -1
        }
        let mid = (i + j) >> 1
        let l = find(nums, i, mid - 1)
        if l != -1 {
            return l
        }
        if nums[mid] == mid {
            return mid
        }
        return find(nums, mid + 1, j)
    }
}
