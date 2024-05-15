class Solution {
    func trap(_ height: [Int]) -> Int {
        let n = height.count
        if n < 3 {
            return 0
        }
        
        var left = [Int](repeating: 0, count: n)
        var right = [Int](repeating: 0, count: n)
        
        left[0] = height[0]
        right[n - 1] = height[n - 1]

        for i in 1..<n {
            left[i] = max(left[i - 1], height[i])
        }

        for i in stride(from: n - 2, through: 0, by: -1) {
            right[i] = max(right[i + 1], height[i])
        }

        var ans = 0
        for i in 0..<n {
            ans += min(left[i], right[i]) - height[i]
        }
        
        return ans
    }
}