class Solution {
    private var memo: [[Bool?]] = []
    private var s: [Character] = []
    private var p: [Character] = []
    private var m: Int = 0
    private var n: Int = 0

    func isMatch(_ s: String, _ p: String) -> Bool {
        self.s = Array(s)
        self.p = Array(p)
        self.m = s.count
        self.n = p.count
        self.memo = Array(repeating: Array(repeating: nil, count: n + 1), count: m + 1)
        return dfs(0, 0)
    }

    private func dfs(_ i: Int, _ j: Int) -> Bool {
        if j >= n {
            return i == m
        }
        if let res = memo[i][j] {
            return res
        }
        var res = false
        if j + 1 < n && p[j + 1] == "*" {
            res = dfs(i, j + 2) || (i < m && (s[i] == p[j] || p[j] == ".") && dfs(i + 1, j))
        } else {
            res = i < m && (s[i] == p[j] || p[j] == ".") && dfs(i + 1, j + 1)
        }
        memo[i][j] = res
        return res
    }
}