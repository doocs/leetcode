class Solution {
    func tictactoe(_ board: [String]) -> String {
        let n = board.count
        var rows = Array(repeating: 0, count: n)
        var cols = Array(repeating: 0, count: n)
        var diagonal = 0, antiDiagonal = 0
        var hasEmptyGrid = false
        
        for i in 0..<n {
            for j in 0..<n {
                let c = Array(board[i])[j]
                if c == " " {
                    hasEmptyGrid = true
                    continue
                }
                let value = c == "X" ? 1 : -1
                rows[i] += value
                cols[j] += value
                if i == j {
                    diagonal += value
                }
                if i + j == n - 1 {
                    antiDiagonal += value
                }
                if abs(rows[i]) == n || abs(cols[j]) == n || abs(diagonal) == n || abs(antiDiagonal) == n {
                    return String(c)
                }
            }
        }
        
        return hasEmptyGrid ? "Pending" : "Draw"
    }
}
