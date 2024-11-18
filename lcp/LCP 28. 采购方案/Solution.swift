class Solution {
    func purchasePlans(_ nums: [Int], _ target: Int) -> Int {
        let mod = 1_000_000_007
        let nums = nums.sorted()
        var res = 0
        var i = 0
        var j = nums.count - 1
        
        while i < j {
            if nums[i] + nums[j] > target {
                j -= 1
            } else {
                res = (res + j - i) % mod
                i += 1
            }
        }
        
        return res
    }
}