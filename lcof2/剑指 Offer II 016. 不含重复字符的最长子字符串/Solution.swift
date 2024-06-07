class Solution {
    func lengthOfLongestSubstring(_ s: String) -> Int {
        var ss = Array(repeating: false, count: 128)
        var ans = 0
        var j = s.startIndex
        
        for i in s.indices {
            let c = s[i]
            while ss[Int(c.asciiValue!)] {
                ss[Int(s[j].asciiValue!)] = false
                j = s.index(after: j)
            }
            ans = max(ans, s.distance(from: j, to: i) + 1)
            ss[Int(c.asciiValue!)] = true
        }
        return ans
    }
}