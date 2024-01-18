class Solution {
    func maxScore(_ cardPoints: [Int], _ k: Int) -> Int {
        let n = cardPoints.count
        var s = cardPoints.suffix(k).reduce(0, +)
        var ans = s
        for i in 0..<k {
            s += cardPoints[i] - cardPoints[n - k + i]
            ans = max(ans, s)
        }
        return ans
    }
}
