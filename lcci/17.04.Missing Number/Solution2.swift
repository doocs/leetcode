class Solution {
    func missingNumber(_ nums: [Int]) -> Int {
        let n = nums.count
        return n * (n + 1) / 2 - nums.reduce(0, +)
    }
}
