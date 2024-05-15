class Solution {
    func findClosest(_ words: [String], _ word1: String, _ word2: String) -> Int {
        let inf = Int.max / 2
        var i = inf
        var j = -inf
        var ans = inf

        for (k, word) in words.enumerated() {
            if word == word1 {
                i = k
            } else if word == word2 {
                j = k
            }
            ans = min(ans, abs(i - j))
        }

        return ans
    }
}
