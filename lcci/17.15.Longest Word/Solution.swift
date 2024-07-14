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
    func longestWord(_ words: [String]) -> String {
        var words = words.sorted(by: { $0.count < $1.count || ($0.count == $1.count && $0 > $1) })
        let trie = Trie()
        
        var dfs: ((String) -> Bool)!
        dfs = { w in
            if w.isEmpty {
                return true
            }
            for i in 1...w.count {
                if trie.search(String(w.prefix(i))) && dfs(String(w.suffix(w.count - i))) {
                    return true
                }
            }
            return false
        }
        
        var ans = ""
        for w in words {
            if dfs(w) {
                ans = w
            }
            trie.insert(w)
        }
        return ans
    }
}
