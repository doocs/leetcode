class Solution {
    func myPow(_ x: Double, _ n: Int) -> Double {
        return n >= 0 ? qpow(x, Int64(n)) : 1 / qpow(x, -Int64(n))
    }

    private func qpow(_ a: Double, _ n: Int64) -> Double {
        var ans: Double = 1
        var base: Double = a
        var exponent: Int64 = n
        
        while exponent > 0 {
            if (exponent & 1) == 1 {
                ans *= base
            }
            base *= base
            exponent >>= 1
        }
        
        return ans
    }
}