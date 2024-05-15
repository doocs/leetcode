class Solution {
    func add(_ a: Int, _ b: Int) -> Int {
        var a = a
        var b = b
        var sum = 0
        var carry = 0
        
        while b != 0 {
            sum = a ^ b
            carry = (a & b) << 1
            a = sum
            b = carry
        }
        
        return a
    }
}