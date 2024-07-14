class Solution {
    func masterMind(_ solution: String, _ guess: String) -> [Int] {
        var x = 0
        var y = 0
        var cnt1: [Character: Int] = [:]
        var cnt2: [Character: Int] = [:]
        
        for i in solution.indices {
            let a = solution[i]
            let b = guess[i]
            if a == b {
                x += 1
            }
            cnt1[a, default: 0] += 1
            cnt2[b, default: 0] += 1
        }
        
        let colors = "RYGB"
        for c in colors {
            let minCount = min(cnt1[c, default: 0], cnt2[c, default: 0])
            y += minCount
        }
        
        return [x, y - x]
    }
}