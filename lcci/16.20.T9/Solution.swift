class Solution {
    func getValidT9Words(_ num: String, _ words: [String]) -> [String] {
        let s = "22233344455566677778889999"
        var d = Array(repeating: 0, count: 26)
        for i in 0..<26 {
            d[i] = Int(s[s.index(s.startIndex, offsetBy: i)].asciiValue! - Character("0").asciiValue!)
        }
        var ans: [String] = []
        let n = num.count
        for w in words {
            var ok = true
            for i in 0..<n {
                let numChar = Int(num[num.index(num.startIndex, offsetBy: i)].asciiValue! - Character("0").asciiValue!)
                if d[Int(w[w.index(w.startIndex, offsetBy: i)].asciiValue! - Character("a").asciiValue!)] != numChar {
                    ok = false
                    break
                }
            }
            if ok {
                ans.append(w)
            }
        }
        return ans
    }
}