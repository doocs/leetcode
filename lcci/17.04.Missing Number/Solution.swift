class Solution {
    func missingNumber(_ nums: [Int]) -> Int {
        let nums = nums.sorted()
        for (i, x) in nums.enumerated() {
            if i != x {
                return i
            }
        }
        return nums.count
    }
}
