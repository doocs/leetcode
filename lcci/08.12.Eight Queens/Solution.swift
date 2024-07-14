class Solution {
    private var ans: [[String]] = []
    private var col: [Int] = Array(repeating: 0, count: 0)
    private var dg: [Int] = Array(repeating: 0, count: 0)
    private var udg: [Int] = Array(repeating: 0, count: 0)
    private var g: [[String]] = Array(repeating: Array(repeating: ".", count: 0), count: 0)
    private var n: Int = 0

    func solveNQueens(_ n: Int) -> [[String]] {
        self.n = n
        col = Array(repeating: 0, count: n)
        dg = Array(repeating: 0, count: n * 2)
        udg = Array(repeating: 0, count: n * 2)
        g = Array(repeating: Array(repeating: ".", count: n), count: n)
        dfs(0)
        return ans
    }

    private func dfs(_ i: Int) {
        guard i < n else {
            let t = g.map { $0.joined() }
            ans.append(t)
            return
        }
        for j in 0..<n {
            if col[j] + dg[i + j] + udg[n - i + j] == 0 {
                g[i][j] = "Q"
                col[j] = 1
                dg[i + j] = 1
                udg[n - i + j] = 1
                dfs(i + 1)
                col[j] = 0
                dg[i + j] = 0
                udg[n - i + j] = 0
                g[i][j] = "."
            }
        }
    }

}