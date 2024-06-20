class MagicDictionary {
    private var words: Set<String>
    private var counter: [String: Int]

    init() {
        words = Set<String>()
        counter = [String: Int]()
    }

    func buildDict(_ dictionary: [String]) {
        for word in dictionary {
            words.insert(word)
            for pattern in patterns(word) {
                counter[pattern, default: 0] += 1
            }
        }
    }

    func search(_ searchWord: String) -> Bool {
        for pattern in patterns(searchWord) {
            let count = counter[pattern, default: 0]
            if count > 1 || (count == 1 && !words.contains(searchWord)) {
                return true
            }
        }
        return false
    }

    private func patterns(_ word: String) -> [String] {
        var result = [String]()
        var chars = Array(word)
        for i in 0..<chars.count {
            let originalChar = chars[i]
            chars[i] = "*"
            result.append(String(chars))
            chars[i] = originalChar
        }
        return result
    }
}

/**
 * Example usage:
 * let obj = MagicDictionary()
 * obj.buildDict(["hello", "hallo", "leetcode"])
 * let param_2 = obj.search("hello")
 * let param_3 = obj.search("hhllo")
 * let param_4 = obj.search("hell")
 * let param_5 = obj.search("leetcoded")
 */
