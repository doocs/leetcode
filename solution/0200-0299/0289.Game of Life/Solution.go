func gameOfLife(board [][]int) {
	m, n := len(board), len(board[0])
	dirs := [8][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			cnt := 0
			for _, dir := range dirs {
				x, y := i+dir[0], j+dir[1]
				if x >= 0 && x < m && y >= 0 && y < n && (board[x][y] == 1 || board[x][y] == 2) {
					cnt++
				}
			}
			if board[i][j] == 1 && (cnt < 2 || cnt > 3) {
				board[i][j] = 2
			} else if board[i][j] == 0 && cnt == 3 {
				board[i][j] = 3
			}
		}
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			board[i][j] %= 2
		}
	}
}