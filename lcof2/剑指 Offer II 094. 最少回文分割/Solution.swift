class Solution {
    func minCut(_ s: String) -> Int {
        let n = s.count
        let sArray = Array(s)
        
        var g = Array(repeating: Array(repeating: true, count: n), count: n)
        
        for i in stride(from: n - 1, through: 0, by: -1) {
            for j in i + 1..<n {
                g[i][j] = sArray[i] == sArray[j] && g[i + 1][j - 1]
            }
        }
        
        var f = Array(repeating: 0, count: n)
        for i in 0..<n {
            f[i] = i
        }
        
        for i in 1..<n {
            for j in 0...i {
                if g[j][i] {
                    f[i] = min(f[i], j > 0 ? 1 + f[j - 1] : 0)
                }
            }
        }
        
        return f[n - 1]
    }
}