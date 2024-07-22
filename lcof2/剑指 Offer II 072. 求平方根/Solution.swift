class Solution {
    func mySqrt(_ x: Int) -> Int {
        if x == 0 {
            return 0
        }
        var left = 0
        var right = x
        while left < right {
            let mid = (left + right + 1) / 2
            if mid <= x / mid {
                left = mid
            } else {
                right = mid - 1
            }
        }
        return left
    }
}
