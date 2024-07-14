class Solution {
    func findSquare(_ matrix: [[Int]]) -> [Int] {
        let n = matrix.count
        var down = Array(repeating: Array(repeating: 0, count: n), count: n)
        var right = Array(repeating: Array(repeating: 0, count: n), count: n)
        
        for i in stride(from: n - 1, through: 0, by: -1) {
            for j in stride(from: n - 1, through: 0, by: -1) {
                if matrix[i][j] == 0 {
                    down[i][j] = (i + 1 < n) ? down[i + 1][j] + 1 : 1
                    right[i][j] = (j + 1 < n) ? right[i][j + 1] + 1 : 1
                }
            }
        }
        
        for k in stride(from: n, through: 1, by: -1) {
            for i in 0...(n - k) {
                for j in 0...(n - k) {
                    if down[i][j] >= k && right[i][j] >= k &&
                       right[i + k - 1][j] >= k && down[i][j + k - 1] >= k {
                        return [i, j, k]
                    }
                }
            }
        }
        
        return []
    }
}