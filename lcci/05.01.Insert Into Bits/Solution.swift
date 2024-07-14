class Solution {
    func insertBits(_ N: Int, _ M: Int, _ i: Int, _ j: Int) -> Int {
        var result = N

        for k in i...j {
            result &= ~(1 << k)
        }

        return result | (M << i)
    }
}