class Solution {
    private var nums: [Int] = []
    
    func search(_ nums: [Int], _ target: Int) -> Int {
        self.nums = nums
        let leftIndex = search(target)
        let rightIndex = search(target + 1)
        return rightIndex - leftIndex
    }
    
    private func search(_ x: Int) -> Int {
        var left = 0
        var right = nums.count
        while left < right {
            let mid = (left + right) / 2
            if nums[mid] >= x {
                right = mid
            } else {
                left = mid + 1
            }
        }
        return left
    }
}