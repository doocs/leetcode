class Solution {
    func isUnique(_ astr: String) -> Bool {
        var mask = 0
        for c in astr {
            let i = Int(c.asciiValue! - Character("a").asciiValue!)
            if (mask >> i) & 1 != 0 {
                return false
            }
            mask |= 1 << i
        }
        return true
    }
}
