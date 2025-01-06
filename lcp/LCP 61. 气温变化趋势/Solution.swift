class Solution {
    func temperatureTrend(_ temperatureA: [Int], _ temperatureB: [Int]) -> Int {
        var maxTrend = 0
        var currentTrend = 0
        
        for i in 0..<temperatureA.count - 1 {
            let changeA = temperatureA[i + 1] - temperatureA[i]
            let changeB = temperatureB[i + 1] - temperatureB[i]
            
            if (changeA == 0 && changeB == 0) || (changeA * changeB > 0) {
                currentTrend += 1
                maxTrend = max(maxTrend, currentTrend)
            } else {
                currentTrend = 0
            }
        }
        
        return maxTrend
    }
}