class Solution {
    func smallestDifference(_ a: [Int], _ b: [Int]) -> Int {
        let sortedB = b.sorted()
        var ans = Int.max
        
        for x in a {
            let j = search(sortedB, x)
            if j < sortedB.count {
                ans = min(ans, abs(sortedB[j] - x))
            }
            if j > 0 {
                ans = min(ans, abs(x - sortedB[j - 1]))
            }
        }
        
        return ans
    }
    
    private func search(_ nums: [Int], _ x: Int) -> Int {
        var l = 0
        var r = nums.count
        while l < r {
            let mid = (l + r) / 2
            if nums[mid] >= x {
                r = mid
            } else {
                l = mid + 1
            }
        }
        return l
    }
}