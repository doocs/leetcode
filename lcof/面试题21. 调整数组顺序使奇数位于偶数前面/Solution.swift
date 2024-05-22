class Solution {
    func exchange(_ nums: [Int]) -> [Int] {
        var nums = nums
        var j = 0
        
        for i in 0..<nums.count {
            if nums[i] % 2 == 1 {
                let temp = nums[i]
                nums[i] = nums[j]
                nums[j] = temp
                j += 1
            }
        }
        
        return nums
    }
}