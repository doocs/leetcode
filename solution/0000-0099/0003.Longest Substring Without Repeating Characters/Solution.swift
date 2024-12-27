class Solution {
    func lengthOfLongestSubstring(_ s: String) -> Int {
        let n = s.count
        var ans = 0
        var cnt = [Int](repeating: 0, count: 128)
        var l = 0
        let sArray = Array(s)
        for r in 0..<n {
            cnt[Int(sArray[r].asciiValue!)] += 1
            while cnt[Int(sArray[r].asciiValue!)] > 1 {
                cnt[Int(sArray[l].asciiValue!)] -= 1
                l += 1
            }
            ans = max(ans, r - l + 1)
        }
        return ans
    }
}
