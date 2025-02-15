class Solution {
    func canPermutePalindrome(_ s: String) -> Bool {
        var vis = Set<Character>()
        for c in s {
            if vis.contains(c) {
                vis.remove(c)
            } else {
                vis.insert(c)
            }
        }
        return vis.count < 2
    }
}
