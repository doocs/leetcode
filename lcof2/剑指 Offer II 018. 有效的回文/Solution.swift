class Solution {
    func isPalindrome(_ s: String) -> Bool {
        var i = s.startIndex
        var j = s.index(before: s.endIndex)

        while i < j {
            while i < j && !s[i].isLetter && !s[i].isNumber {
                i = s.index(after: i)
            }
            while i < j && !s[j].isLetter && !s[j].isNumber {
                j = s.index(before: j)
            }
            if i >= j {
                break
            }
            if s[i].lowercased() != s[j].lowercased() {
                return false
            }
            i = s.index(after: i)
            j = s.index(before: j)
        }
        return true
    }
}
