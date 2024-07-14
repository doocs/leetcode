class TrieNode {
    var children: [TrieNode?] = Array(repeating: nil, count: 26)
    var isEndOfWord = false
}

class Trie {
    private let root = TrieNode()

    func insert(_ word: String) {
        var node = root
        for char in word {
            let index = Int(char.asciiValue! - Character("a").asciiValue!)
            if node.children[index] == nil {
                node.children[index] = TrieNode()
            }
            node = node.children[index]!
        }
        node.isEndOfWord = true
    }

    func search(_ sentence: Array<Character>, start: Int, end: Int) -> Bool {
        var node = root
        for i in start...end {
            let index = Int(sentence[i].asciiValue! - Character("a").asciiValue!)
            guard let nextNode = node.children[index] else {
                return false
            }
            node = nextNode
        }
        return node.isEndOfWord
    }
}

class Solution {
    func respace(_ dictionary: [String], _ sentence: String) -> Int {
        let n = sentence.count
        guard n > 0 else { return 0 }
        let trie = Trie()
        dictionary.forEach { trie.insert($0) }
        let chars = Array(sentence)
        var dp = Array(repeating: Int.max, count: n + 1)
        dp[0] = 0
        for i in 1...n {
            dp[i] = dp[i - 1] + 1
            for j in 0..<i {
                if trie.search(chars, start: j, end: i - 1) {
                    dp[i] = min(dp[i], dp[j])
                }
            }
        }
        return dp[n]
    }
}
