class Solution {
    func calculate(_ s: String) -> Int {
        var x = 1
        var y = 0
        for c in s {
            if c == "A" {
                x = x * 2 + y
            } else if c == "B" {
                y = y * 2 + x
            }
        }
        return x + y
    }
}