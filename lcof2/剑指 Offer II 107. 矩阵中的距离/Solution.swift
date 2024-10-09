class Solution {
    func updateMatrix(_ mat: [[Int]]) -> [[Int]] {
        let m = mat.count
        let n = mat[0].count
        var ans = Array(repeating: Array(repeating: -1, count: n), count: m)
        var queue = [(Int, Int)]()

        for i in 0..<m {
            for j in 0..<n {
                if mat[i][j] == 0 {
                    ans[i][j] = 0
                    queue.append((i, j))
                }
            }
        }

        let dirs = [-1, 0, 1, 0, -1]

        while !queue.isEmpty {
            let (i, j) = queue.removeFirst()
            for d in 0..<4 {
                let x = i + dirs[d]
                let y = j + dirs[d + 1]
                if x >= 0 && x < m && y >= 0 && y < n && ans[x][y] == -1 {
                    ans[x][y] = ans[i][j] + 1
                    queue.append((x, y))
                }
            }
        }

        return ans
    }
}
