class Solution {
    private var s: String = ""

    func validPalindrome(_ s: String) -> Bool {
        self.s = s
        var i = s.startIndex
        var j = s.index(before: s.endIndex)
        
        while i < j {
            if s[i] != s[j] {
                return check(s.index(after: i), j) || check(i, s.index(before: j))
            }
            i = s.index(after: i)
            j = s.index(before: j)
        }
        return true
    }

    private func check(_ i: String.Index, _ j: String.Index) -> Bool {
        var i = i
        var j = j
        while i < j {
            if s[i] != s[j] {
                return false
            }
            i = s.index(after: i)
            j = s.index(before: j)
        }
        return true
    }
}