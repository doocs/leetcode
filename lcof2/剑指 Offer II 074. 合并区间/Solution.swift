class Solution {
    func merge(_ intervals: [[Int]]) -> [[Int]] {
        guard !intervals.isEmpty else { return [] }
        
        let intervals = intervals.sorted { $0[0] < $1[0] }
        var result: [[Int]] = []
        
        var currentInterval = intervals[0]
        for interval in intervals.dropFirst() {
            if currentInterval[1] < interval[0] {
                result.append(currentInterval)
                currentInterval = interval
            } else {
                currentInterval[1] = max(currentInterval[1], interval[1])
            }
        }
        result.append(currentInterval)
        
        return result
    }
}
