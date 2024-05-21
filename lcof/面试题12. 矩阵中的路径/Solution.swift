class Solution {
    private var board: [[Character]]
    private var word: String
    private var m: Int
    private var n: Int

    init() {
        self.board = []
        self.word = ""
        self.m = 0
        self.n = 0
    }

    func exist(_ board: [[Character]], _ word: String) -> Bool {
        self.board = board
        self.word = word
        m = board.count
        n = board[0].count

        for i in 0..<m {
            for j in 0..<n {
                if dfs(i, j, 0) {
                    return true
                }
            }
        }
        return false
    }

    private func dfs(_ i: Int, _ j: Int, _ k: Int) -> Bool {
        if k == word.count {
            return true
        }
        if i < 0 || i >= m || j < 0 || j >= n || board[i][j] != word[word.index(word.startIndex, offsetBy: k)] {
            return false
        }

        let temp = board[i][j]
        board[i][j] = " "
        let dirs = [-1, 0, 1, 0, -1]
        var ans = false

        for l in 0..<4 {
            let ni = i + dirs[l]
            let nj = j + dirs[l + 1]
            if dfs(ni, nj, k + 1) {
                ans = true
                break
            }
        }

        board[i][j] = temp
        return ans
    }
}
