class Solution {
    func rotate(_ matrix: inout [[Int]]) {
        let n = matrix.count

        for i in 0..<(n >> 1) {
            for j in 0..<n {
                let t = matrix[i][j]
                matrix[i][j] = matrix[n - i - 1][j]
                matrix[n - i - 1][j] = t
            }
        }

        for i in 0..<n {
            for j in 0..<i {
                let t = matrix[i][j]
                matrix[i][j] = matrix[j][i]
                matrix[j][i] = t
            }
        }
    }
}
