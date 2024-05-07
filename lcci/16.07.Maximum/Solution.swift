class Solution {
    func maximum(_ a: Int, _ b: Int) -> Int {
        let diff = Int64(a) - Int64(b)
        let k = Int((diff >> 63) & 1)
        return a * (k ^ 1) + b * k
    }
}