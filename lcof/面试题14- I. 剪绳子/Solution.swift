class Solution {
    func cuttingRope(_ n: Int) -> Int {
        var f = [Int](repeating: 0, count: n + 1)
        f[1] = 1
        for i in 2...n {
            for j in 1..<i {
                f[i] = max(f[i], max(f[i - j] * j, (i - j) * j))
            }
        }
        return f[n]
    }
}