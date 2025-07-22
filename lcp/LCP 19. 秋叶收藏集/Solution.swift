class Solution {
    func minimumOperations(_ leaves: String) -> Int {
        let n = leaves.count
        let inf = Int.max / 2
        var f = Array(repeating: [inf, inf, inf], count: n)
        let leavesArray = Array(leaves)
        
        f[0][0] = leavesArray[0] == "r" ? 0 : 1
        
        for i in 1..<n {
            if leavesArray[i] == "r" {
                f[i][0] = f[i - 1][0]
                f[i][1] = min(f[i - 1][0], f[i - 1][1]) + 1
                f[i][2] = min(f[i - 1][1], f[i - 1][2])
            } else {
                f[i][0] = f[i - 1][0] + 1
                f[i][1] = min(f[i - 1][0], f[i - 1][1])
                f[i][2] = min(f[i - 1][1], f[i - 1][2]) + 1
            }
        }
        
        return f[n - 1][2]
    }
}