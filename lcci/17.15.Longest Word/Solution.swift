class Trie {
    var children = [Trie?](repeating: nil, count: 26)
    var isEnd = false

    func insert(_ word: String) {
        var node = self
        for ch in word {
            let index = Int(ch.asciiValue! - Character("a").asciiValue!)
            if node.children[index] == nil {
                node.children[index] = Trie()
            }
            node = node.children[index]!
        }
        node.isEnd = true
    }

    func search(_ word: String) -> Bool {
        var node = self
        for ch in word {
            let index = Int(ch.asciiValue! - Character("a").asciiValue!)
            if node.children[index] == nil {
                return false
            }
            node = node.children[index]!
        }
        return node.isEnd
    }
}

class Solution {
    private let trie = Trie()

    func longestWord(_ words: [String]) -> String {
        let sortedWords = words.sorted { a, b in
            if a.count != b.count {
                return a.count < b.count
            }
            return a > b
        }

        var ans = ""
        for word in sortedWords {
            if dfs(word, "") {
                ans = word
            }
            trie.insert(word)
        }
        return ans
    }

    private func dfs(_ word: String, _ prefix: String) -> Bool {
        if prefix.isEmpty {
            return true
        }
        for i in 1...prefix.count {
            let index = prefix.index(prefix.startIndex, offsetBy: i)
            let subPrefix = String(prefix[..<index])
            if trie.search(subPrefix) && dfs(word, String(prefix[index...])) {
                return true
            }
        }
        return false
    }
}