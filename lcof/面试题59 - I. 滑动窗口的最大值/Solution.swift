class Solution {
    func maxSlidingWindow(_ nums: [Int], _ k: Int) -> [Int] {
        let n = nums.count
        var ans = [Int]()
        var deque = [Int]()
        
        for i in 0..<n {
            if !deque.isEmpty && deque.first! < i - k + 1 {
                deque.removeFirst()
            }
            
            while !deque.isEmpty && nums[deque.last!] <= nums[i] {
                deque.removeLast()
            }
            
            deque.append(i)
            
            if i >= k - 1 {
                ans.append(nums[deque.first!])
            }
        }
        
        return ans
    }
}