class Solution {
    func getMaxMatrix(_ matrix: [[Int]]) -> [Int] {
        let m = matrix.count, n = matrix[0].count
        var s = Array(repeating: Array(repeating: 0, count: n), count: m + 1)
        
        for i in 0..<m {
            for j in 0..<n {
                s[i + 1][j] = s[i][j] + matrix[i][j]
            }
        }
        
        var mx = matrix[0][0]
        var ans = [0, 0, 0, 0]
        
        for i1 in 0..<m {
            for i2 in i1..<m {
                var nums = [Int](repeating: 0, count: n)
                for j in 0..<n {
                    nums[j] = s[i2 + 1][j] - s[i1][j]
                }
                
                var start = 0
                var f = nums[0]
                for j in 1..<n {
                    if f > 0 {
                        f += nums[j]
                    } else {
                        f = nums[j]
                        start = j
                    }
                    if f > mx {
                        mx = f
                        ans = [i1, start, i2, j]
                    }
                }
            }
        }
        return ans
    }
}