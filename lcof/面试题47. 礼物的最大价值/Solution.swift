class Solution {
    func maxValue(_ grid: [[Int]]) -> Int {
        let m = grid.count
        let n = grid[0].count
        var f = [[Int]](repeating: [Int](repeating: 0, count: n + 1), count: m + 1)
        
        for i in 1...m {
            for j in 1...n {
                f[i][j] = max(f[i - 1][j], f[i][j - 1]) + grid[i - 1][j - 1]
            }
        }
        
        return f[m][n]
    }
}