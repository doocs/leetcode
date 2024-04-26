class Solution {
    func convertInteger(_ A: Int, _ B: Int) -> Int {
        return (Int32(A) ^ Int32(B)).nonzeroBitCount
    }
}
