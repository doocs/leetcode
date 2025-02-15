class Solution {
    func reverseBits(_ num: Int) -> Int {
        var ans = 0
        var cnt = 0
        var j = 0

        for i in 0..<32 {
            cnt += (num >> i & 1 ^ 1)
            while cnt > 1 {
                cnt -= (num >> j & 1 ^ 1)
                j += 1
            }
            ans = max(ans, i - j + 1)
        }

        return ans
    }
}
