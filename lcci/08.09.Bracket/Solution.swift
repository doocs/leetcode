class Solution {
    private var ans: [String] = []
    private var n: Int = 0

    func generateParenthesis(_ n: Int) -> [String] {
        self.n = n
        dfs(l: 0, r: 0, t: "")
        return ans
    }

    private func dfs(l: Int, r: Int, t: String) {
        if l > n || r > n || l < r {
            return
        }
        if l == n && r == n {
            ans.append(t)
            return
        }
        dfs(l: l + 1, r: r, t: t + "(")
        dfs(l: l, r: r + 1, t: t + ")")
    }
}
