class Solution {
    func minFlipsMonoIncr(_ s: String) -> Int {
        let n = s.count
        var left0 = 0, right0 = 0
        let chars = Array(s)
        
        for char in chars {
            if char == "0" {
                right0 += 1
            }
        }
        
        var ans = min(right0, n - right0)
        
        for i in 1...n {
            let x = chars[i - 1] == "0" ? 0 : 1
            right0 -= x ^ 1
            left0 += x ^ 1
            ans = min(ans, i - left0 + right0)
        }
        
        return ans
    }
}