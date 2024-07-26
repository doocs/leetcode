func candyCrush(board [][]int) [][]int {
	m := len(board)
	n := len(board[0])
	run := true

	for run {
		run = false
		for i := 0; i < m; i++ {
			for j := 2; j < n; j++ {
				if board[i][j] != 0 && abs(board[i][j]) == abs(board[i][j-1]) && abs(board[i][j]) == abs(board[i][j-2]) {
					run = true
					val := abs(board[i][j])
					board[i][j] = -val
					board[i][j-1] = -val
					board[i][j-2] = -val
				}
			}
		}
		for j := 0; j < n; j++ {
			for i := 2; i < m; i++ {
				if board[i][j] != 0 && abs(board[i][j]) == abs(board[i-1][j]) && abs(board[i][j]) == abs(board[i-2][j]) {
					run = true
					val := abs(board[i][j])
					board[i][j] = -val
					board[i-1][j] = -val
					board[i-2][j] = -val
				}
			}
		}
		if run {
			for j := 0; j < n; j++ {
				k := m - 1
				for i := m - 1; i >= 0; i-- {
					if board[i][j] > 0 {
						board[k][j] = board[i][j]
						k--
					}
				}
				for k >= 0 {
					board[k][j] = 0
					k--
				}
			}
		}
	}

	return board
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}