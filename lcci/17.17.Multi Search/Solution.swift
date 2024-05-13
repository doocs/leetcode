class TrieNode {
    var idx: Int
    var children: [TrieNode?]

    init() {
        self.idx = -1
        self.children = Array(repeating: nil, count: 26)
    }
}

class Trie {
    private let root: TrieNode

    init() {
        self.root = TrieNode()
    }

    func insert(_ word: String, _ index: Int) {
        var node = root
        for ch in word {
            let i = Int(ch.asciiValue! - Character("a").asciiValue!)
            if node.children[i] == nil {
                node.children[i] = TrieNode()
            }
            node = node.children[i]!
        }
        node.idx = index
    }

    func search(_ word: String) -> [Int] {
        var node = root
        var results = [Int]()
        for ch in word {
            let i = Int(ch.asciiValue! - Character("a").asciiValue!)
            if node.children[i] == nil {
                break
            }
            node = node.children[i]!
            if node.idx != -1 {
                results.append(node.idx)
            }
        }
        return results
    }
}

class Solution {
    func multiSearch(_ big: String, _ smalls: [String]) -> [[Int]] {
        let trie = Trie()
        for (index, small) in smalls.enumerated() {
            trie.insert(small, index)
        }

        var results = Array(repeating: [Int](), count: smalls.count)
        let bigChars = Array(big)

        for i in 0..<bigChars.count {
            let substring = String(bigChars[i...])
            let indices = trie.search(substring)
            for index in indices {
                results[index].append(i)
            }
        }

        return results
    }
}