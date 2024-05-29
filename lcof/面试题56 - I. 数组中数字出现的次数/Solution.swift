class Solution {
    func singleNumbers(_ nums: [Int]) -> [Int] {
        var xorSum = 0
        for num in nums {
            xorSum ^= num
        }
        
        let lowBit = xorSum & -xorSum
        var a = 0
        for num in nums {
            if (num & lowBit) != 0 {
                a ^= num
            }
        }
        
        let b = xorSum ^ a
        return [a, b]
    }
}