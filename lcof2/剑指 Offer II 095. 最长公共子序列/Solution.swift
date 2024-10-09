class Solution {
    func longestCommonSubsequence(_ text1: String, _ text2: String) -> Int {
        let m = text1.count, n = text2.count
        let text1Array = Array(text1)
        let text2Array = Array(text2)
        var f = Array(repeating: Array(repeating: 0, count: n + 1), count: m + 1)
        
        for i in 1...m {
            for j in 1...n {
                if text1Array[i - 1] == text2Array[j - 1] {
                    f[i][j] = f[i - 1][j - 1] + 1
                } else {
                    f[i][j] = max(f[i - 1][j], f[i][j - 1])
                }
            }
        }
        return f[m][n]
    }
}