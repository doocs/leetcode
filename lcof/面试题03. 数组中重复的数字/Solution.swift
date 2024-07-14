class Solution {
    func findRepeatNumber(_ nums: [Int]) -> Int {
        let sortedNums = nums.sorted()
        for i in 0..<sortedNums.count - 1 {
            if sortedNums[i] == sortedNums[i + 1] {
                return sortedNums[i]
            }
        }
        return -1
    }
}