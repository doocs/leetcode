class Solution {
    func minNumBooths(_ demand: [String]) -> Int {
        var maxBooths = [Int](repeating: 0, count: 26)

        for day in demand {
            var dailyCount = [Int](repeating: 0, count: 26)
            for char in day {
                let index = Int(char.asciiValue! - Character("a").asciiValue!)
                dailyCount[index] += 1
            }
            for i in 0..<26 {
                maxBooths[i] = max(maxBooths[i], dailyCount[i])
            }
        }

        return maxBooths.reduce(0, +)
    }
}