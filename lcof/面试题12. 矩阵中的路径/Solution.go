func exist(board [][]byte, word string) bool {
    if len(board) == 0 {
        return false
    }
    //标记数组
    isVisited := make([][]bool,len(board))
    for i := 0; i < len(board); i++ {
        isVisited[i] = make([]bool, len(board[0]))
    }
    for i := 0; i < len(board); i++ {
        for j := 0; j < len(board[0]); j++ {
            if board[i][j] == word[0] {
                if bfs(board,i,j,isVisited,word,0) {
                    return true
                }
            }
        }
    }
    return false
}

func bfs(board [][]byte, i,j int, isVisited [][]bool, word string, index int) bool {
    if index == len(word) {
        return true
    }
    if i < 0 || j < 0 || i == len(board) || j == len(board[0]) || isVisited[i][j] || board[i][j] != word[index] {
        return false
    }
    isVisited[i][j] = true
    res := bfs(board, i+1, j, isVisited, word, index+1) ||
        bfs(board, i, j+1, isVisited, word, index+1) ||
        bfs(board, i-1, j, isVisited, word, index+1) ||
        bfs(board, i, j-1, isVisited, word, index+1)
    isVisited[i][j] = false
    return res
}