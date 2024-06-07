class Solution {
    func spiralOrder(_ matrix: [[Int]]) -> [Int] {
        guard !matrix.isEmpty && !matrix[0].isEmpty else {
            return []
        }
        
        let m = matrix.count
        let n = matrix[0].count
        var vis = Array(repeating: Array(repeating: false, count: n), count: m)
        var ans = [Int]()
        var i = 0, j = 0, k = 0
        let dirs = [0, 1, 0, -1, 0]
        
        for _ in 0..<m*n {
            ans.append(matrix[i][j])
            vis[i][j] = true
            var x = i + dirs[k], y = j + dirs[k + 1]
            if x < 0 || y < 0 || x >= m || y >= n || vis[x][y] {
                k = (k + 1) % 4
                x = i + dirs[k]
                y = j + dirs[k + 1]
            }
            i = x
            j = y
        }
        
        return ans
    }
}