class Solution {
    func minNumber(_ nums: [Int]) -> String {
        let sortedNums = nums.map { String($0) }
                             .sorted { $0 + $1 < $1 + $0 }
        return sortedNums.joined()
    }
}