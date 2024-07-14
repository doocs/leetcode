class Solution {
    func isStraight(_ nums: [Int]) -> Bool {
        var vis = Array(repeating: false, count: 14)
        var mi = 20, mx = -1
        for x in nums {
            if x == 0 {
                continue
            }
            if vis[x] {
                return false
            }
            vis[x] = true
            mi = min(mi, x)
            mx = max(mx, x)
        }
        return mx - mi <= 4
    }
}
