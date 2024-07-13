class Solution {
    func dicesProbability(_ n: Int) -> [Double] {
        var f = Array(repeating: Array(repeating: 0, count: 6 * n + 1), count: n + 1)
        for j in 1...6 {
            f[1][j] = 1
        }
        if n > 1 {
            for i in 2...n {
                for j in i...(6 * i) {
                    for k in 1...6 {
                        if j >= k {
                            f[i][j] += f[i - 1][j - k]
                        }
                    }
                }
            }
        }
        var m = 1.0
        for _ in 0..<n {
            m *= 6.0
        }
        var ans = Array(repeating: 0.0, count: 5 * n + 1)
        for j in n...(6 * n) {
            ans[j - n] = Double(f[n][j]) / m
        }
        return ans
    }
}