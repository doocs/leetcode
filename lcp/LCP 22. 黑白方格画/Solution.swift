class Solution {
    func paintingPlan(_ n: Int, _ k: Int) -> Int {
        if k == 0 || k == n * n {
            return 1
        }
        
        func combination(_ n: Int, _ r: Int) -> Int {
            guard r <= n else { return 0 }
            if r == 0 || r == n { return 1 }
            var result = 1
            for i in 0..<r {
                result = result * (n - i) / (i + 1)
            }
            return result
        }
        
        var ans = 0
        
        for i in 0...n {
            for j in 0...n {
                let paintedCells = n * (i + j) - i * j
                if paintedCells == k {
                    ans += combination(n, i) * combination(n, j)
                }
            }
        }
        
        return ans
    }
}