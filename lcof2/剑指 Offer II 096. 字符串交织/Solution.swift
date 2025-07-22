class Solution {
    private var memo = [String: Bool]()
    private var s1: [Character] = []
    private var s2: [Character] = []
    private var s3: [Character] = []
    private var m = 0
    private var n = 0

    func isInterleave(_ s1: String, _ s2: String, _ s3: String) -> Bool {
        m = s1.count
        n = s2.count
        if m + n != s3.count {
            return false
        }
        self.s1 = Array(s1)
        self.s2 = Array(s2)
        self.s3 = Array(s3)
        return dfs(0, 0)
    }

    private func dfs(_ i: Int, _ j: Int) -> Bool {
        if i >= m && j >= n {
            return true
        }
        let key = "\(i),\(j)"
        if let cached = memo[key] {
            return cached
        }
        let k = i + j
        var ans = false
        if i < m && s1[i] == s3[k] && dfs(i + 1, j) {
            ans = true
        }
        if !ans && j < n && s2[j] == s3[k] && dfs(i, j + 1) {
            ans = true
        }
        memo[key] = ans
        return ans
    }
}