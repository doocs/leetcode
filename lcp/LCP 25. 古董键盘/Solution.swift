class Solution {
    func keyboard(_ k: Int, _ n: Int) -> Int {
        let mod = 1_000_000_007
        var c = Array(repeating: Array(repeating: 0, count: k + 1), count: n + 1)
        for i in 0...n {
            c[i][0] = 1
        }
        
        for i in 1...n {
            for j in 1...k {
                c[i][j] = (c[i - 1][j - 1] + c[i - 1][j]) % mod
            }
        }
        
        var f = Array(repeating: Array(repeating: 0, count: 27), count: n + 1)
        for j in 0..<27 {
            f[0][j] = 1
        }
        
        for i in 1...n {
            for j in 1..<27 {
                for h in 0...min(i, k) {
                    f[i][j] = (f[i][j] + (f[i - h][j - 1] * c[i][h]) % mod) % mod
                }
            }
        }
        
        return f[n][26]
    }
}