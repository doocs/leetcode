class Solution {
    private var ans = [String]()
    private var wordList: [String]
    private var endWord: String
    private var vis: [Bool]

    init(wordList: [String], endWord: String) {
        self.wordList = wordList
        self.endWord = endWord
        self.vis = Array(repeating: false, count: wordList.count)
    }

    func findLadders(_ beginWord: String) -> [String] {
        ans.append(beginWord)
        if dfs(beginWord) {
            return ans
        } else {
            return []
        }
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
            vis[i] = false
        }
        return false
    }

    private func check(_ s: String, _ t: String) -> Bool {
        if s.count != t.count {
            return false
        }
        var count = 0
        for (sc, tc) in zip(s, t) {
            if sc != tc {
                count += 1
                if count > 1 {
                    return false
                }
            }
        }
        return count == 1
    }
}