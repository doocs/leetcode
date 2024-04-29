class Solution {
    func multiply(_ A: Int, _ B: Int) -> Int {
        if B == 1 {
            return A
        }
        if (B & 1) == 1 {
            return (multiply(A, B >> 1) << 1) + A
        }
        return multiply(A, B >> 1) << 1
    }
}