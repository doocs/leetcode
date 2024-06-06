class Solution {
    func maximalRectangle(_ matrix: [[Character]]) -> Int {
        guard let firstRow = matrix.first else {
            return 0
        }
        
        let n = firstRow.count
        var heights = [Int](repeating: 0, count: n)
        var ans = 0
        
        for row in matrix {
            for j in 0..<n {
                if row[j] == "1" {
                    heights[j] += 1
                } else {
                    heights[j] = 0
                }
            }
            ans = max(ans, largestRectangleArea(heights))
        }
        
        return ans
    }

    private func largestRectangleArea(_ heights: [Int]) -> Int {
        var res = 0
        let n = heights.count
        var stack = [Int]()
        var left = [Int](repeating: -1, count: n)
        var right = [Int](repeating: n, count: n)
        
        for i in 0..<n {
            while !stack.isEmpty && heights[stack.last!] >= heights[i] {
                right[stack.removeLast()] = i
            }
            left[i] = stack.isEmpty ? -1 : stack.last!
            stack.append(i)
        }
        
        for i in 0..<n {
            res = max(res, heights[i] * (right[i] - left[i] - 1))
        }
        
        return res
    }
}