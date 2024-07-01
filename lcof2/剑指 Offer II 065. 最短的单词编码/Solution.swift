class Trie {
    var children = [Trie?](repeating: nil, count: 26)
}

class Solution {
    func minimumLengthEncoding(_ words: [String]) -> Int {
        let root = Trie()
        
        for word in words {
            var current = root
            for char in word.reversed() {
                let index = Int(char.asciiValue! - Character("a").asciiValue!)
                if current.children[index] == nil {
                    current.children[index] = Trie()
                }
                current = current.children[index]!
            }
        }
        
        return dfs(root, 1)
    }
    
    private func dfs(_ current: Trie, _ length: Int) -> Int {
        var isLeaf = true
        var result = 0
        
        for child in current.children {
            if let child = child {
                isLeaf = false
                result += dfs(child, length + 1)
            }
        }
        
        if isLeaf {
            result += length
        }
        
        return result
    }
}