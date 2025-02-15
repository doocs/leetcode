class Solution {
    func CheckPermutation(_ s1: String, _ s2: String) -> Bool {
        if s1.count != s2.count {
            return false
        }

        var cnt = [Int](repeating: 0, count: 26)

        for char in s1 {
            cnt[Int(char.asciiValue! - Character("a").asciiValue!)] += 1
        }

        for char in s2 {
            let index = Int(char.asciiValue! - Character("a").asciiValue!)
            if cnt[index] == 0 {
                return false
            }
            cnt[index] -= 1
        }

        return true
    }
}
