class Solution {
    func missingNumber(_ nums: [Int]) -> Int {
        let n = nums.count
        let expectedSum = n * (n + 1) / 2
        let actualSum = nums.reduce(0, +)
        return expectedSum - actualSum
    }
}
