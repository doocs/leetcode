class Solution {
    func findAnagrams(_ s: String, _ p: String) -> [Int] {
        let m = s.count
        let n = p.count
        var ans = [Int]()
        
        if m < n {
            return ans
        }
        
        var cnt1 = Array(repeating: 0, count: 26)
        var cnt2 = Array(repeating: 0, count: 26)
        
        let aAsciiValue = Character("a").asciiValue!
        
        for i in 0..<n {
            let sIdx = Int(s.utf8[s.utf8.index(s.utf8.startIndex, offsetBy: i)] - aAsciiValue)
            let pIdx = Int(p.utf8[p.utf8.index(p.utf8.startIndex, offsetBy: i)] - aAsciiValue)
            cnt1[sIdx] += 1
            cnt2[pIdx] += 1
        }
        
        if cnt1 == cnt2 {
            ans.append(0)
        }
        
        for i in n..<m {
            let sIdxNew = Int(s.utf8[s.utf8.index(s.utf8.startIndex, offsetBy: i)] - aAsciiValue)
            let sIdxOld = Int(s.utf8[s.utf8.index(s.utf8.startIndex, offsetBy: i - n)] - aAsciiValue)
            cnt1[sIdxNew] += 1
            cnt1[sIdxOld] -= 1
            
            if cnt1 == cnt2 {
                ans.append(i - n + 1)
            }
        }
        
        return ans
    }
}