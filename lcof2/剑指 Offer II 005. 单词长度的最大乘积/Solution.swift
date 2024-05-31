class Solution {
    func maxProduct(_ words: [String]) -> Int {
        let n = words.count
        var masks = [Int](repeating: 0, count: n)
        
        for i in 0..<n {
            for c in words[i] {
                masks[i] |= 1 << (c.asciiValue! - Character("a").asciiValue!)
            }
        }
        
        var maxProduct = 0
        for i in 0..<n {
            for j in i+1..<n {
                if masks[i] & masks[j] == 0 {
                    maxProduct = max(maxProduct, words[i].count * words[j].count)
                }
            }
        }
        
        return maxProduct
    }
}