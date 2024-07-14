class Solution {
    func swapNumbers(_ numbers: [Int]) -> [Int] {
        var numbers = numbers
        numbers[0] ^= numbers[1]
        numbers[1] ^= numbers[0]
        numbers[0] ^= numbers[1]
        return numbers
    }
}
