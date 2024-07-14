class Solution {
    func checkInclusion(_ s1: String, _ s2: String) -> Bool {
        let m = s1.count
        let n = s2.count
        if m > n {
            return false
        }
        
        var cnt1 = [Int](repeating: 0, count: 26)
        var cnt2 = [Int](repeating: 0, count: 26)
        let aAscii = Character("a").asciiValue!
        
        for i in 0..<m {
            cnt1[Int(s1[s1.index(s1.startIndex, offsetBy: i)].asciiValue! - aAscii)] += 1
            cnt2[Int(s2[s2.index(s2.startIndex, offsetBy: i)].asciiValue! - aAscii)] += 1
        }
        
        if cnt1 == cnt2 {
            return true
        }
        
        for i in m..<n {
            cnt2[Int(s2[s2.index(s2.startIndex, offsetBy: i)].asciiValue! - aAscii)] += 1
            cnt2[Int(s2[s2.index(s2.startIndex, offsetBy: i - m)].asciiValue! - aAscii)] -= 1
            
            if cnt1 == cnt2 {
                return true
            }
        }
        
        return false
    }
}