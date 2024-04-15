class Solution {
    func compressString(_ S: String) -> String {
        let n = S.count
        var compressed = ""
        var i = 0
        
        while i < n {
            var j = i
            let currentChar = S[S.index(S.startIndex, offsetBy: i)]
            while j < n && S[S.index(S.startIndex, offsetBy: j)] == currentChar {
                j += 1
            }
            compressed += "\(currentChar)\(j - i)"
            i = j
        }
        
        return compressed.count < n ? compressed : S
    }
}
