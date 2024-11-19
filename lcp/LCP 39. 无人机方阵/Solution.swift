class Solution {
    func minimumSwitchingTimes(_ source: [[Int]], _ target: [[Int]]) -> Int {
        var count = [Int: Int]()
        
        for row in source {
            for num in row {
                count[num, default: 0] += 1
            }
        }
        
        for row in target {
            for num in row {
                count[num, default: 0] -= 1
            }
        }
        
        var result = 0
        for value in count.values {
            result += abs(value)
        }
        
        return result / 2
    }
}
