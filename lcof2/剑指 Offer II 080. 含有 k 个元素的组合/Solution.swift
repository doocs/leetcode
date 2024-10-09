class Solution {
    func combine(_ n: Int, _ k: Int) -> [[Int]] {
        var res = [[Int]]()
        dfs(1, n, k, [], &res)
        return res
    }
    
    private func dfs(_ start: Int, _ n: Int, _ k: Int, _ current: [Int], _ res: inout [[Int]]) {
        if current.count == k {
            res.append(current)
            return
        }
        
        if start > n {
            return
        }
        
        for i in start...n {
            var newCurrent = current
            newCurrent.append(i)
            dfs(i + 1, n, k, newCurrent, &res)
        }
    }
}
