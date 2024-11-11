class Solution {
    func numWays(_ n: Int, _ relation: [[Int]], _ k: Int) -> Int {
        var f = Array(repeating: 0, count: n)
        f[0] = 1
        var steps = k
        
        while steps > 0 {
            var g = Array(repeating: 0, count: n)
            for r in relation {
                let a = r[0], b = r[1]
                g[b] += f[a]
            }
            f = g
            steps -= 1
        }
        
        return f[n - 1]
    }
}