class Solution {
    func canPermutePalindrome(_ s: String) -> Bool {
        var cnt = [Character: Int]()
        for char in s {
            cnt[char, default: 0] += 1
        }
        
        var sum = 0
        for count in cnt.values {
            sum += count % 2
        }
        
        return sum < 2
    }
}
