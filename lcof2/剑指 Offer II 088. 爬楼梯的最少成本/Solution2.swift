class Solution {
    func minCostClimbingStairs(_ cost: [Int]) -> Int {
        var a = 0
        var b = 0
        for i in 1..<cost.count {
            let c = min(a + cost[i - 1], b + cost[i])
            a = b
            b = c
        }
        return b
    }
}