class Solution {
    func longestWord(_ words: [String]) -> String {
        var s: Set<String> = Set(words)
        var words = words
        words.sort { (a, b) -> Bool in
            if a.count == b.count {
                return a < b
            } else {
                return a.count > b.count
            }
        }

        func dfs(_ w: String) -> Bool {
            if w.isEmpty {
                return true
            }
            for k in 1...w.count {
                let prefix = String(w.prefix(k))
                if s.contains(prefix) && dfs(String(w.dropFirst(k))) {
                    return true
                }
            }
            return false
        }

        for w in words {
            s.remove(w)
            if dfs(w) {
                return w
            }
        }

        return ""
    }
}
