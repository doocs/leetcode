class Solution {
    func findContinuousSequence(_ target: Int) -> [[Int]] {
        var l = 1, r = 2
        var result = [[Int]]()
        
        while l < r {
            let sum = (l + r) * (r - l + 1) / 2
            if sum == target {
                var sequence = [Int]()
                for i in l...r {
                    sequence.append(i)
                }
                result.append(sequence)
                l += 1
            } else if sum < target {
                r += 1
            } else {
                l += 1
            }
        }
        
        return result
    }
}