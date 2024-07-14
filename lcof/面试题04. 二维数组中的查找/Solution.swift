class Solution {
    func findNumberIn2DArray(_ matrix: [[Int]], _ target: Int) -> Bool {
        for row in matrix {
            if let _ = row.firstIndex(of: target) {
                return true
            }
        }
        return false
    }
}
