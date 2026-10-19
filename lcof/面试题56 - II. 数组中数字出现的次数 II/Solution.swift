class Solution {
    func singleNumber(_ nums: [Int]) -> Int {
        var cnt = [Int](repeating: 0, count: 32)

        for num in nums {
            var x = num
            for i in 0..<32 {
                cnt[i] += x & 1
                x >>= 1
            }
        }

        var ans = 0
        for i in 0..<32 {
            if cnt[i] % 3 == 1 {
                if i == 31 {
                    ans -= 1 << i
                } else {
                    ans |= 1 << i
                }
            }
        }

        return ans
    }
}
