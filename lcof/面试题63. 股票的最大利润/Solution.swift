class Solution {
    func maxProfit(_ prices: [Int]) -> Int {
        var mi = Int.max
        var ans = 0
        
        for x in prices {
            ans = max(ans, x - mi)
            mi = min(mi, x)
        }
        
        return ans
    }
}