class Solution {
    func setZeroes(_ matrix: inout [[Int]]) {
        let m = matrix.count
        guard m > 0 else { return }
        let n = matrix[0].count
        var rows = Array(repeating: false, count: m)
        var cols = Array(repeating: false, count: n)
        
        for i in 0..<m {
            for j in 0..<n {
                if matrix[i][j] == 0 {
                    rows[i] = true
                    cols[j] = true
                }
            }
        }
        
        for i in 0..<m {
            for j in 0..<n {
                if rows[i] || cols[j] {
                    matrix[i][j] = 0
                }
            }
        }
    }
}
