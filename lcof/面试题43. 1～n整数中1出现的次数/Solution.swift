class Solution {
    private var digits = [Int](repeating: 0, count: 12)
    private var memo = [[Int?]](repeating: [Int?](repeating: nil, count: 12), count: 12)

    func countDigitOne(_ n: Int) -> Int {
        var n = n
        var i = 0
        while n > 0 {
            digits[i] = n % 10
            n /= 10
            i += 1
        }
        return dfs(i - 1, 0, true)
    }

    private func dfs(_ pos: Int, _ count: Int, _ limit: Bool) -> Int {
        if pos < 0 {
            return count
        }
        if !limit && memo[pos][count] != nil {
            return memo[pos][count]!
        }
        let upperLimit = limit ? digits[pos] : 9
        var ans = 0
        for i in 0...upperLimit {
            ans += dfs(pos - 1, count + (i == 1 ? 1 : 0), limit && i == upperLimit)
        }
        if !limit {
            memo[pos][count] = ans
        }
        return ans
    }
}