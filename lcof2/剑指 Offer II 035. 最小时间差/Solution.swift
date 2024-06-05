class Solution {
    func findMinDifference(_ timePoints: [String]) -> Int {
        if timePoints.count > 24 * 60 {
            return 0
        }
        
        var mins = [Int]()
        
        for t in timePoints {
            let time = t.split(separator: ":").map { Int($0)! }
            mins.append(time[0] * 60 + time[1])
        }
        
        mins.sort()
        mins.append(mins[0] + 24 * 60)
        
        var ans = Int.max
        for i in 1..<mins.count {
            ans = min(ans, mins[i] - mins[i - 1])
        }
        
        return ans
    }
}