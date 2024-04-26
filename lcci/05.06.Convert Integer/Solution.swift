class Solution {
    func convertInteger(_ A: Int, _ B: Int) -> Int {
        return (A ^ B).nonzeroBitCount
    }
}