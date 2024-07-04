class Solution {
    func findMaximumXOR(_ numbers: [Int]) -> Int {
        var max = 0
        var mask = 0

        for i in stride(from: 30, through: 0, by: -1) {
            let current = 1 << i
            mask ^= current
            var set = Set<Int>()
            for num in numbers {
                set.insert(mask & num)
            }
            let flag = max | current
            for prefix in set {
                if set.contains(prefix ^ flag) {
                    max = flag
                    break
                }
            }
        }
        return max
    }
}