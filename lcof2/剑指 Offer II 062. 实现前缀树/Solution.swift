class Trie {
    private var children: [Trie?]
    private var isEnd: Bool

    init() {
        self.children = Array(repeating: nil, count: 26)
        self.isEnd = false
    }

    func insert(_ word: String) {
        var node = self
        for char in word {
            let index = Int(char.asciiValue! - Character("a").asciiValue!)
            if node.children[index] == nil {
                node.children[index] = Trie()
            }
            node = node.children[index]!
        }
        node.isEnd = true
    }

    func search(_ word: String) -> Bool {
        if let node = searchPrefix(word) {
            return node.isEnd
        }
        return false
    }

    func startsWith(_ prefix: String) -> Bool {
        return searchPrefix(prefix) != nil
    }

    private func searchPrefix(_ prefix: String) -> Trie? {
        var node = self
        for char in prefix {
            let index = Int(char.asciiValue! - Character("a").asciiValue!)
            if node.children[index] == nil {
                return nil
            }
            node = node.children[index]!
        }
        return node
    }
}
/**
 * Your Trie object will be instantiated and called as such:
 * let trie = Trie()
 * trie.insert(word);
 * trie.search(word);
 * trie.startsWith(prefix);
 */