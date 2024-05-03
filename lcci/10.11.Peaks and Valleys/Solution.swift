class Solution {
    func wiggleSort(_ nums: inout [Int]) {
        nums.sort()
        
        let n = nums.count
        
        for i in stride(from: 0, to: n - 1, by: 2) {
            let temp = nums[i]
            nums[i] = nums[i + 1]
            nums[i + 1] = temp
        }
    }
}