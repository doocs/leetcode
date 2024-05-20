class Solution {
    func minArray(_ numbers: [Int]) -> Int {
        var l = 0
        var r = numbers.count - 1
        while l < r {
            let m = (l + r) / 2
            if numbers[m] > numbers[r] {
                l = m + 1
            } else if numbers[m] < numbers[r] {
                r = m
            } else {
                r -= 1
            }
        }
        return numbers[l]
    }
}