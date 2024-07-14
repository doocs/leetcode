class Solution {
    func findString(_ words: [String], _ s: String) -> Int {
        return dfs(words, s, 0, words.count - 1)
    }

    private func dfs(_ words: [String], _ s: String, _ i: Int, _ j: Int) -> Int {
        if i > j {
            return -1
        }
        let mid = (i + j) >> 1
        let left = dfs(words, s, i, mid - 1)
        if left != -1 {
            return left
        }
        if words[mid] == s {
            return mid
        }
        return dfs(words, s, mid + 1, j)
    }
}
