class Solution {
    func waysToChange(_ n: Int) -> Int {
        let mod = Int(1e9 + 7)
        let coins = [25, 10, 5, 1]
        var f = Array(repeating: Array(repeating: 0, count: n + 1), count: 5)
        f[0][0] = 1

        for i in 1...4 {
            for j in 0...n {
                f[i][j] = f[i - 1][j]
                if j >= coins[i - 1] {
                    f[i][j] = (f[i][j] + f[i][j - coins[i - 1]]) % mod
                }
            }
        }
        return f[4][n]
    }
}
