class Solution {
    private var a = [Int](repeating: 0, count: 12)
    private var dp = [[Int]](repeating: [Int](repeating: -1, count: 12), count: 12)

    func numberOf2sInRange(_ n: Int) -> Int {
        var n = n
        var len = 0
        while n > 0 {
            len += 1
            a[len] = n % 10
            n /= 10
        }
        for i in 0..<12 {
            dp[i] = [Int](repeating: -1, count: 12)
        }
        return dfs(len, 0, true)
    }

    private func dfs(_ pos: Int, _ cnt: Int, _ limit: Bool) -> Int {
        if pos <= 0 {
            return cnt
        }
        if !limit && dp[pos][cnt] != -1 {
            return dp[pos][cnt]
        }
        let up = limit ? a[pos] : 9
        var ans = 0
        for i in 0...up {
            ans += dfs(pos - 1, cnt + (i == 2 ? 1 : 0), limit && i == up)
        }
        if !limit {
            dp[pos][cnt] = ans
        }
        return ans
    }
}
