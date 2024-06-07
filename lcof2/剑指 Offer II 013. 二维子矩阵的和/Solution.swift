class NumMatrix {
    private var prefixSum: [[Int]]

    init(_ matrix: [[Int]]) {
        let m = matrix.count
        let n = matrix[0].count
        prefixSum = Array(repeating: Array(repeating: 0, count: n + 1), count: m + 1)
        
        for i in 1...m {
            for j in 1...n {
                prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1] + matrix[i - 1][j - 1]
            }
        }
    }

    func sumRegion(_ row1: Int, _ col1: Int, _ row2: Int, _ col2: Int) -> Int {
        return prefixSum[row2 + 1][col2 + 1] - prefixSum[row2 + 1][col1] - prefixSum[row1][col2 + 1] + prefixSum[row1][col1]
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * let obj = NumMatrix(matrix);
 * let param_1 = obj.sumRegion(row1,col1,row2,col2);
 */