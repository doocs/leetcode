class Solution {
    func longestConsecutive(_ nums: [Int]) -> Int {
        let numSet: Set<Int> = Set(nums)
        var longestStreak = 0
        
        for num in nums {
            if !numSet.contains(num - 1) {
                var currentNum = num
                var currentStreak = 1
                
                while numSet.contains(currentNum + 1) {
                    currentNum += 1
                    currentStreak += 1
                }
                
                longestStreak = max(longestStreak, currentStreak)
            }
        }
        
        return longestStreak
    }
}