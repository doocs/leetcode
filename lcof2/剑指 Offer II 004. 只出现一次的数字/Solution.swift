class Solution {
    func singleNumber(_ nums: [Int]) -> Int {
        var ans = 0
        for i in 0..<32 {
            var cnt = 0
            for num in nums {
                cnt += (num >> i) & 1
            }
            cnt %= 3
            ans |= cnt << i
        }
        return ans
    }
}