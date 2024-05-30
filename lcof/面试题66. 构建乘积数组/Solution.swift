class Solution {
    func constructArr(_ a: [Int]) -> [Int] {
        let n = a.count
        guard n > 0 else { return [] }
        
        var ans = [Int](repeating: 1, count: n)
        
        var left = 1
        for i in 0..<n {
            ans[i] = left
            left *= a[i]
        }
        
        var right = 1
        for i in (0..<n).reversed() {
            ans[i] *= right
            right *= a[i]
        }
        
        return ans
    }
}