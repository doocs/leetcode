class Solution {
    func numSubarrayProductLessThanK(_ nums: [Int], _ k: Int) -> Int {
        if k <= 1 { return 0 }
        
        var product: Int = 1
        var ans: Int = 0
        var left: Int = 0
        
        for right in 0..<nums.count {
            product *= nums[right]
            while product >= k {
                product /= nums[left]
                left += 1
            }
            ans += right - left + 1
        }
        
        return ans
    }
}