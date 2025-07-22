class Solution {
    func minTime(_ time: [Int], _ m: Int) -> Int {
        var left = 0
        var right = time.reduce(0, +)
        
        while left < right {
            let mid = (left + right) / 2
            if check(mid, time, m) {
                right = mid
            } else {
                left = mid + 1
            }
        }
        return left
    }
    
    private func check(_ t: Int, _ time: [Int], _ m: Int) -> Bool {
        var sum = 0
        var maxTime = 0
        var days = 1
        
        for x in time {
            sum += x
            maxTime = max(maxTime, x)
            if sum - maxTime > t {
                sum = x
                maxTime = x
                days += 1
            }
        }
        return days <= m
    }
}
