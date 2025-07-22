class Solution {
    private var n: Int = 0
    private var s: String = ""
    private var ans: [String] = []
    private var t: [String] = []

    func restoreIpAddresses(_ s: String) -> [String] {
        n = s.count
        self.s = s
        dfs(0)
        return ans
    }

    private func dfs(_ i: Int) {
        if i >= n && t.count == 4 {
            ans.append(t.joined(separator: "."))
            return
        }
        if i >= n || t.count >= 4 {
            return
        }
        var x = 0
        let chars = Array(s)
        for j in i..<min(i + 3, n) {
            x = x * 10 + Int(chars[j].wholeNumberValue!)
            if x > 255 || (chars[i] == "0" && i != j) {
                break
            }
            t.append(String(chars[i...j]))
            dfs(j + 1)
            t.removeLast()
        }
    }
}