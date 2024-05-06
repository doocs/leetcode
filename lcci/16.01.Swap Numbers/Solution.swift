class Solution {
    func swapNumbers(_ numbers: inout [Int]) -> [Int] {
        numbers[0] ^= numbers[1]
        numbers[1] ^= numbers[0]
        numbers[0] ^= numbers[1]
        return numbers
    }
}