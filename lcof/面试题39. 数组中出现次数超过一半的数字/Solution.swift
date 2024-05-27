class Solution {
    func majorityElement(_ nums: [Int]) -> Int {
        var cnt = 0
        var m = 0
        
        for v in nums {
            if cnt == 0 {
                m = v
                cnt = 1
            } else {
                cnt += (m == v ? 1 : -1)
            }
        }
        return m
    }
}