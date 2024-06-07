class Solution {
    func add(_ a: Int, _ b: Int) -> Int {
        var a = a
        var b = b
        while b != 0 {
            let c = (a & b) << 1
            a ^= b
            b = c
        }
        return a
    }
}