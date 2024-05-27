class Solution {
    private var ans: [String] = []
    private var cs: [Character] = []

    func permutation(_ s: String) -> [String] {
        cs = Array(s)
        dfs(0)
        return ans
    }

    private func dfs(_ i: Int) {
        if i == cs.count - 1 {
            ans.append(String(cs))
            return
        }
        var vis: Set<Character> = []
        for j in i..<cs.count {
            if !vis.contains(cs[j]) {
                vis.insert(cs[j])
                swap(i, j)
                dfs(i + 1)
                swap(i, j)
            }
        }
    }

    private func swap(_ i: Int, _ j: Int) {
        let t = cs[i]
        cs[i] = cs[j]
        cs[j] = t
    }
}