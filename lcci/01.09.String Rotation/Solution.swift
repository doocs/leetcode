class Solution {
    func isFlippedString(_ s1: String, _ s2: String) -> Bool {
        return (s1.isEmpty && s2.isEmpty) || (s1.count == s2.count && (s1 + s1).contains(s2))
    }
}
