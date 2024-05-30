class Solution {
    private var n: Int = 0
    private var s: [Character] = []
    private var memo: [Int?] = []

    func translateNum(_ num: Int) -> Int {
        s = Array(String(num))
        n = s.count
        memo = [Int?](repeating: nil, count: n)
        return dfs(0)
    }

    private func dfs(_ i: Int) -> Int {
        if i >= n - 1 {
            return 1
        }
        if let cachedResult = memo[i] {
            return cachedResult
        }
        var ans = dfs(i + 1)
        if s[i] == "1" || (s[i] == "2" && s[i + 1] < "6") {
            ans += dfs(i + 2)
        }
        memo[i] = ans
        return ans
    }
}