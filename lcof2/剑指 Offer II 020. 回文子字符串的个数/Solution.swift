class Solution {
    private var s: String = ""

    func countSubstrings(_ s: String) -> Int {
        var ans = 0
        self.s = s
        let length = s.count
        for i in 0..<length {
            ans += countPalindromes(i, i)
            ans += countPalindromes(i, i + 1)
        }
        return ans
    }

    private func countPalindromes(_ i: Int, _ j: Int) -> Int {
        var cnt = 0
        var i = i
        var j = j
        let chars = Array(s)
        
        while i >= 0 && j < chars.count && chars[i] == chars[j] {
            cnt += 1
            i -= 1
            j += 1
        }
        
        return cnt
    }
}