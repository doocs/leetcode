class Solution {
    func respace(_ dictionary: [String], _ sentence: String) -> Int {
        let dict = Set(dictionary)
        let n = sentence.count
        var dp = [Int](repeating: 0, count: n + 1)
        let sentenceArray = Array(sentence)
        
        for i in 1...n {
            dp[i] = dp[i - 1] + 1
            for j in 0..<i {
                let sub = String(sentenceArray[j..<i])
                if dict.contains(sub) {
                    dp[i] = min(dp[i], dp[j])
                }
            }
        }
        return dp[n]
    }
}
