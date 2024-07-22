class Solution {
    func minEatingSpeed(_ piles: [Int], _ h: Int) -> Int {
        var left = 1
        var right = piles.max() ?? 0
        
        while left < right {
            let mid = (left + right) / 2
            var hours = 0
            
            for pile in piles {
                hours += (pile + mid - 1) / mid
            }
            
            if hours <= h {
                right = mid
            } else {
                left = mid + 1
            }
        }
        
        return left
    }
}