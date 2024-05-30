class Solution {
    func addBinary(_ a: String, _ b: String) -> String {
        var result = ""
        var carry = 0
        var i = a.count - 1, j = b.count - 1
        
        let aChars = Array(a)
        let bChars = Array(b)
        
        while i >= 0 || j >= 0 || carry > 0 {
            let digitA = i >= 0 ? Int(String(aChars[i]))! : 0
            let digitB = j >= 0 ? Int(String(bChars[j]))! : 0
            
            carry += digitA + digitB
            result = "\(carry % 2)" + result
            carry /= 2
            
            i -= 1
            j -= 1
        }
        
        return result
    }
}
