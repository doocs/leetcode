class Solution {
    func findMaxLength(_ nums: [Int]) -> Int {
        var d: [Int: Int] = [0: -1]
        var ans = 0
        var s = 0
        
        for i in 0..<nums.count {
            s += nums[i] == 0 ? -1 : 1
            if let prevIndex = d[s] {
                ans = max(ans, i - prevIndex)
            } else {
                d[s] = i
            }
        }
        
        return ans
    }
}