class Solution {
    func CheckPermutation(_ s1: String, _ s2: String) -> Bool {
        let s1 = s1.sorted()
        let s2 = s2.sorted()
        return s1 == s2
    }
}
