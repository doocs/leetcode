class Solution {
    func ladderLength(_ beginWord: String, _ endWord: String, _ wordList: [String]) -> Int {
        var words = Set(wordList)
        var queue = [beginWord]
        var ans = 1
        
        while !queue.isEmpty {
            for _ in 0..<queue.count {
                let s = queue.removeFirst()
                var chars = Array(s)
                for j in 0..<chars.count {
                    let ch = chars[j]
                    for k in 0..<26 {
                        chars[j] = Character(UnicodeScalar(k + 97)!)
                        let t = String(chars)
                        if !words.contains(t) {
                            continue
                        }
                        if t == endWord {
                            return ans + 1
                        }
                        queue.append(t)
                        words.remove(t)
                    }
                    chars[j] = ch
                }
            }
            ans += 1
        }
        return 0
    }
}