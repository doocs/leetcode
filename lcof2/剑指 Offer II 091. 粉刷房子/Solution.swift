class Solution {
    func minCost(_ costs: [[Int]]) -> Int {
        var r = 0, g = 0, b = 0
        for cost in costs {
            let _r = r, _g = g, _b = b
            r = min(_g, _b) + cost[0]
            g = min(_r, _b) + cost[1]
            b = min(_r, _g) + cost[2]
        }
        return min(r, min(g, b))
    }
}
