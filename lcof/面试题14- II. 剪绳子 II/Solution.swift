class Solution {
    private let mod = 1000000007

    func cuttingRope(_ n: Int) -> Int {
        if n < 4 {
            return n - 1
        }
        if n % 3 == 0 {
            return qpow(3, n / 3)
        }
        if n % 3 == 1 {
            return (4 * qpow(3, n / 3 - 1)) % mod
        }
        return (2 * qpow(3, n / 3)) % mod
    }

    private func qpow(_ a: Int, _ n: Int) -> Int {
        var a = a
        var n = n
        var ans: Int = 1
        while n > 0 {
            if (n & 1) == 1 {
                ans = (ans * a) % mod
            }
            a = (a * a) % mod
            n >>= 1
        }
        return ans
    }
}
