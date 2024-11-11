class Solution {
    func numWays(_ n: Int, _ relation: [[Int]], _ k: Int) -> Int {
        var f = Array(repeating: Array(repeating: 0, count: n), count: k + 1)
        f[0][0] = 1
        
        for i in 1...k {
            for r in relation {
                let a = r[0], b = r[1]
                f[i][b] += f[i - 1][a]
            }
        }
        return f[k][n - 1]
    }
}
