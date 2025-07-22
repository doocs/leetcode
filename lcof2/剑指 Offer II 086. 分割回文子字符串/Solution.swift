class Solution {
    private var n: Int = 0
    private var s: String = ""
    private var f: [[Bool]] = []
    private var t: [String] = []
    private var ans: [[String]] = []

    func partition(_ s: String) -> [[String]] {
        n = s.count
        self.s = s
        f = Array(repeating: Array(repeating: true, count: n), count: n)
        
        let chars = Array(s)
        
        for i in stride(from: n - 1, through: 0, by: -1) {
            for j in i + 1 ..< n {
                f[i][j] = chars[i] == chars[j] && f[i + 1][j - 1]
            }
        }
        
        dfs(0)
        return ans
    }

    private func dfs(_ i: Int) {
        if i == n {
            ans.append(t)
            return
        }
        for j in i ..< n {
            if f[i][j] {
                t.append(String(s[s.index(s.startIndex, offsetBy: i)...s.index(s.startIndex, offsetBy: j)]))
                dfs(j + 1)
                t.removeLast()
            }
        }
    }
}