class Trie {
    var children = [Trie?](repeating: nil, count: 26)
    var isEnd = false
    
    func insert(_ word: String) {
        var node = self
        for c in word {
            let index = Int(c.asciiValue! - Character("a").asciiValue!)
            if node.children[index] == nil {
                node.children[index] = Trie()
            }
            node = node.children[index]!
        }
        node.isEnd = true
    }
}

class Solution {
    private var maxL = 0
    private var maxS = 0
    private var ans: [String]?
    private var trie = Trie()
    private var t = [String]()

    func maxRectangle(_ words: [String]) -> [String]? {
        var d = [Int: [String]]()
        for word in words {
            maxL = max(maxL, word.count)
            trie.insert(word)
            d[word.count, default: []].append(word)
        }
        
        for ws in d.values {
            t.removeAll()
            dfs(ws)
        }
        return ans
    }

    private func dfs(_ ws: [String]) {
        guard let first = ws.first, first.count * maxL > maxS, t.count < maxL else { return }
        for w in ws {
            t.append(w)
            let st = check(t)
            switch st {
            case 0:
                t.removeLast()
            case 1:
                if maxS < t.count * t[0].count {
                    maxS = t.count * t[0].count
                    ans = t
                }
                dfs(ws)
                t.removeLast()
            default:
                dfs(ws)
                t.removeLast()
            }
        }
    }

    private func check(_ mat: [String]) -> Int {
        let m = mat.count, n = mat[0].count
        var result = 1
        for j in 0..<n {
            var node = trie
            for i in 0..<m {
                let index = Int(mat[i][mat[i].index(mat[i].startIndex, offsetBy: j)].asciiValue! - Character("a").asciiValue!)
                guard let nextNode = node.children[index] else { return 0 }
                node = nextNode
            }
            if !node.isEnd {
                result = 2
            }
        }
        return result
    }
}