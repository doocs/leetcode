class Solution {
    func rob(_ nums: [Int]) -> Int {
        let n = nums.count
        if n == 1 {
            return nums[0]
        }
        return max(rob(nums, 0, n - 2), rob(nums, 1, n - 1))
    }

    private func rob(_ nums: [Int], _ l: Int, _ r: Int) -> Int {
        var f = 0, g = 0
        for i in l...r {
            let temp = max(f, g)
            g = f + nums[i]
            f = temp
        }
        return max(f, g)
    }
}