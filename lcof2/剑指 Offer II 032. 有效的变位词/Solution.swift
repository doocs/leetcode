class Solution {
    func isAnagram(_ s: String, _ t: String) -> Bool {
        let m = s.count
        let n = t.count
        if m != n || s == t {
            return false
        }
        
        var cnt = [Int](repeating: 0, count: 26)
        
        for (sc, tc) in zip(s, t) {
            cnt[Int(sc.asciiValue! - Character("a").asciiValue!)] += 1
            cnt[Int(tc.asciiValue! - Character("a").asciiValue!)] -= 1
        }
        
        for x in cnt {
            if x != 0 {
                return false
            }
        }
        
        return true
    }
}