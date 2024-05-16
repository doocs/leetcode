class Solution {
    private var ans: [String] = []
    private var wordList: [String] = []
    private var endWord: String = ""
    private var vis: [Bool] = []
    
    func findLadders(_ beginWord: String, _ endWord: String, _ wordList: [String]) -> [String] {
        self.wordList = wordList
        self.endWord = endWord
        ans.append(beginWord)
        vis = Array(repeating: false, count: wordList.count)
        return dfs(beginWord) ? ans : []
    }
    
    private func dfs(_ s: String) -> Bool {
        if s == endWord {
            return true
        }
        for i in 0..<wordList.count {
            let t = wordList[i]
            if vis[i] || !check(s, t) {
                continue
            }
            vis[i] = true
            ans.append(t)
            if dfs(t) {
                return true
            }
            ans.removeLast()
        }
        return false
    }
    
    private func check(_ s: String, _ t: String) -> Bool {
        if s.count != t.count {
            return false
        }
        var cnt = 0
        for (sc, tc) in zip(s, t) {
            if sc != tc {
                cnt += 1
                if cnt > 1 {
                    return false
                }
            }
        }
        return cnt == 1
    }
}
