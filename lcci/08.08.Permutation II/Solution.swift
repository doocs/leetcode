class Solution {
    private var n: Int = 0
    private var cs: [Character] = []
    private var ans: [String] = []
    private var vis: [Bool] = []
    private var t: String = ""

    func permutation(_ S: String) -> [String] {
        cs = Array(S)
        n = cs.count
        cs.sort()
        vis = Array(repeating: false, count: n)
        dfs(0)
        return ans
    }

    private func dfs(_ i: Int) {
        if i == n {
            ans.append(t)
            return
        }
        for j in 0..<n {
            if vis[j] || (j > 0 && !vis[j - 1] && cs[j] == cs[j - 1]) {
                continue
            }
            vis[j] = true
            t.append(cs[j])
            dfs(i + 1)
            t.removeLast()
            vis[j] = false
        }
    }
}
