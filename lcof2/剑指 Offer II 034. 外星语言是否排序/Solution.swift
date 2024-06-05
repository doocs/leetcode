class Solution {
    func isAlienSorted(_ words: [String], _ order: String) -> Bool {
        var index = [Character: Int]()
        
        for (i, char) in order.enumerated() {
            index[char] = i
        }
        
        for i in 0..<words.count - 1 {
            let w1 = Array(words[i])
            let w2 = Array(words[i + 1])
            let l1 = w1.count
            let l2 = w2.count
            
            for j in 0..<max(l1, l2) {
                let i1 = j >= l1 ? -1 : index[w1[j]]!
                let i2 = j >= l2 ? -1 : index[w2[j]]!
                
                if i1 > i2 {
                    return false
                }
                if i1 < i2 {
                    break
                }
            }
        }
        return true
    }
}