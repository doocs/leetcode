class Solution {
    func lenLongestFibSubseq(_ arr: [Int]) -> Int {
        let n = arr.count
        var mp = [Int: Int]()
        for i in 0..<n {
            mp[arr[i]] = i
        }
        
        var dp = Array(repeating: Array(repeating: 2, count: n), count: n)
        var ans = 0
        
        for i in 0..<n {
            for j in 0..<i {
                let delta = arr[i] - arr[j]
                if let k = mp[delta], k < j {
                    dp[j][i] = dp[k][j] + 1
                    ans = max(ans, dp[j][i])
                }
            }
        }
        
        return ans > 2 ? ans : 0
    }
}