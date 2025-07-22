class Solution {
    private var m = 0
    private var n = 0
    private var chessboard: [String] = []
    
    func flipChess(_ chessboard: [String]) -> Int {
        self.m = chessboard.count
        self.n = chessboard[0].count
        self.chessboard = chessboard
        var ans = 0
        
        for i in 0..<m {
            for j in 0..<n {
                if Array(chessboard[i])[j] == "." {
                    ans = max(ans, bfs(i, j))
                }
            }
        }
        return ans
    }
    
    private func bfs(_ i: Int, _ j: Int) -> Int {
        var queue: [[Int]] = [[i, j]]
        var g = chessboard.map { Array($0) }
        g[i][j] = "X"
        var count = 0
        
        while !queue.isEmpty {
            let p = queue.removeFirst()
            let i = p[0], j = p[1]
            
            for a in -1...1 {
                for b in -1...1 {
                    if a == 0 && b == 0 { continue }
                    
                    var x = i + a, y = j + b
                    while x >= 0 && x < m && y >= 0 && y < n && g[x][y] == "O" {
                        x += a
                        y += b
                    }
                    
                    if x >= 0 && x < m && y >= 0 && y < n && g[x][y] == "X" {
                        x -= a
                        y -= b
                        count += max(abs(x - i), abs(y - j))
                        
                        while x != i || y != j {
                            g[x][y] = "X"
                            queue.append([x, y])
                            x -= a
                            y -= b
                        }
                    }
                }
            }
        }
        return count
    }
}