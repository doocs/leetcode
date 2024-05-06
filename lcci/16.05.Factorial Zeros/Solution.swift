class Solution {
    func trailingZeroes(_ n: Int) -> Int {
        var count = 0
        var number = n
        while number > 0 {
            number /= 5
            count += number
        }
        return count
    }
}