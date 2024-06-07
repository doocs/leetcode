class Solution {
    func firstUniqChar(_ s: String) -> Character {
        var count = [Int](repeating: 0, count: 26)
        let aAsciiValue = Int(Character("a").asciiValue!)
        
        for char in s {
            count[Int(char.asciiValue!) - aAsciiValue] += 1
        }
        
        for char in s {
            if count[Int(char.asciiValue!) - aAsciiValue] == 1 {
                return char
            }
        }
        
        return " "
    }
}