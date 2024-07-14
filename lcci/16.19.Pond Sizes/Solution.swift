class Solution {
    private var m: Int = 0
    private var n: Int = 0
    private var land: [[Int]] = []

    func pondSizes(_ land: [[Int]]) -> [Int] {
        self.land = land
        m = land.count
        n = land[0].count
        var ans: [Int] = []

        for i in 0..<m {
            for j in 0..<n {
                if self.land[i][j] == 0 {
                    ans.append(dfs(i, j))
                }
            }
        }
        return ans.sorted()
    }

    private func dfs(_ i: Int, _ j: Int) -> Int {
        var res = 1
        self.land[i][j] = 1
        for x in max(i - 1, 0)...min(i + 1, m - 1) {
            for y in max(j - 1, 0)...min(j + 1, n - 1) {
                if self.land[x][y] == 0 {
                    res += dfs(x, y)
                }
            }
        }
        return res
    }
}