class Solution {
    func maxAliveYear(_ birth: [Int], _ death: [Int]) -> Int {
        let base = 1900
        var delta = Array(repeating: 0, count: 102) // Array to hold the changes

        for i in 0..<birth.count {
            let start = birth[i] - base
            let end = death[i] - base
            delta[start] += 1
            if end + 1 < delta.count {
                delta[end + 1] -= 1
            }
        }

        var maxAlive = 0, currentAlive = 0, maxYear = 0
        for year in 0..<delta.count {
            currentAlive += delta[year]
            if currentAlive > maxAlive {
                maxAlive = currentAlive
                maxYear = year + base
            }
        }

        return maxYear
    }
}