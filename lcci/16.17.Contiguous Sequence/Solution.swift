class Solution {
    func maxSubArray(_ nums: [Int]) -> Int {
        var ans = Int.min
        var f = Int.min

        for x in nums {
            f = max(f, 0) + x
            ans = max(ans, f)
        }

        return ans
    }
}