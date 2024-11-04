class Solution {
    func longestConsecutive(_ nums: [Int]) -> Int {
        let n = nums.count
        if n < 2 {
            return n
        }
        
        let sortedNums = Array(Set(nums)).sorted()
        var ans = 1
        var currentStreak = 1
        
        for i in 1..<sortedNums.count {
            if sortedNums[i] == sortedNums[i - 1] + 1 {
                currentStreak += 1
                ans = max(ans, currentStreak)
            } else {
                currentStreak = 1
            }
        }
        
        return ans
    }
}