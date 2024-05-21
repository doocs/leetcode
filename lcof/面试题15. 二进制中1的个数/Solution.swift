class Solution {
    func hammingWeight(_ n: UInt32) -> Int {
        var n = n
        var ans = 0
        while n != 0 {
            n &= (n - 1)
            ans += 1
        }
        return ans
    }
}