class Solution {
    func printKMoves(_ K: Int) -> [String] {
        var x1 = 0, y1 = 0, x2 = 0, y2 = 0
        let dirs = [0, 1, 0, -1, 0]
        let d = "RDLU"
        var x = 0, y = 0, p = 0
        var black = Set<[Int]>()
        var K = K
        
        while K > 0 {
            let t = [x, y]
            if black.insert(t).inserted {
                p = (p + 1) % 4
            } else {
                black.remove(t)
                p = (p + 3) % 4
            }
            x += dirs[p]
            y += dirs[p + 1]
            x1 = min(x1, x)
            y1 = min(y1, y)
            x2 = max(x2, x)
            y2 = max(y2, y)
            K -= 1
        }
        
        let m = x2 - x1 + 1
        let n = y2 - y1 + 1
        var g = Array(repeating: Array(repeating: "_", count: n), count: m)
        
        for t in black {
            let i = t[0] - x1
            let j = t[1] - y1
            g[i][j] = "X"
        }
        
        g[x - x1][y - y1] = String(d[d.index(d.startIndex, offsetBy: p)])
        
        return g.map { $0.joined() }
    }
}