class Solution {
    func largestRectangleArea(_ heights: [Int]) -> Int {
        let n = heights.count
        var left = [Int](repeating: -1, count: n)
        var right = [Int](repeating: n, count: n)
        var stack = [Int]()

        for i in 0..<n {
            while !stack.isEmpty && heights[stack.last!] >= heights[i] {
                stack.removeLast()
            }
            if !stack.isEmpty {
                left[i] = stack.last!
            }
            stack.append(i)
        }

        stack.removeAll()

        for i in stride(from: n - 1, through: 0, by: -1) {
            while !stack.isEmpty && heights[stack.last!] >= heights[i] {
                stack.removeLast()
            }
            if !stack.isEmpty {
                right[i] = stack.last!
            }
            stack.append(i)
        }

        var maxArea = 0
        for i in 0..<n {
            maxArea = max(maxArea, (right[i] - left[i] - 1) * heights[i])
        }

        return maxArea
    }
}