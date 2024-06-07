class Solution {
    func singleNumber(_ nums: [Int]) -> Int {
        var bitCounts = [Int](repeating: 0, count: 32)
        
        for num in nums {
            var x = num
            for i in 0..<32 {
                bitCounts[i] += x & 1
                x >>= 1
            }
        }
        
        var result = 0
        for i in 0..<32 {
            if bitCounts[i] % 3 == 1 {
                result |= 1 << i
            }
        }
        
        return result
    }
}