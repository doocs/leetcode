class Solution {
    func missingTwo(_ nums: [Int]) -> [Int] {
        let n = nums.count + 2
        var xor = 0
        
        for num in nums {
            xor ^= num
        }
        
        for i in 1...n {
            xor ^= i
        }
        
        let diff = xor & (-xor)
        
        var a = 0
        
        for num in nums {
            if (num & diff) != 0 {
                a ^= num
            }
        }
        
        for i in 1...n {
            if (i & diff) != 0 {
                a ^= i
            }
        }
        
        let b = xor ^ a
        return [a, b]
    }
}