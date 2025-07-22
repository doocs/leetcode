class Solution {
    func expectNumber(_ scores: [Int]) -> Int {
        let uniqueScores = Set(scores)
        return uniqueScores.count
    }
}
