class Solution {
    private var s: [Character] = []
    private var vis: [Bool] = Array(repeating: false, count: 128)
    private var ans: [String] = []
    private var t: String = ""

    func permutation(_ S: String) -> [String] {
        s = Array(S)
        dfs(0)
        return ans
    }

    private func dfs(_ i: Int) {
        if i == s.count {
            ans.append(t)
            return
        }
        for c in s {
            let index = Int(c.asciiValue!)
            if vis[index] {
                continue
            }
            vis[index] = true
            t.append(c)
            dfs(i + 1)
            t.removeLast()
            vis[index] = false
        }
    }
}
