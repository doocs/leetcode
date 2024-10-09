class Solution {
    func rob(_ nums: [Int]) -> Int {
        let n = nums.count
        if n == 0 { return 0 }
        if n == 1 { return nums[0] }
        
        var f = Array(repeating: 0, count: n + 1)
        f[1] = nums[0]
        
        for i in 2...n {
            f[i] = max(f[i - 1], f[i - 2] + nums[i - 1])
        }
        
        return f[n]
    }
}