class Solution {
    func minCount(_ coins: [Int]) -> Int {
        var ans = 0
        for x in coins {
            ans += (x + 1) >> 1
        }
        return ans
    }
}