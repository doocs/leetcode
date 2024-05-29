class Solution {
    func reverseWords(_ s: String) -> String {
        var words = [String]()
        var i = s.startIndex
        
        while i < s.endIndex {
            while i < s.endIndex && s[i] == " " {
                i = s.index(after: i)
            }
            if i < s.endIndex {
                var t = ""
                var j = i
                while j < s.endIndex && s[j] != " " {
                    t.append(s[j])
                    j = s.index(after: j)
                }
                words.append(t)
                i = j
            }
        }
        
        words.reverse()
        return words.joined(separator: " ")
    }
}