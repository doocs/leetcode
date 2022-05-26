func candyCrush(board [][]int) [][]int {
	m, n := len(board), len(board[0])
	run := true
	for run {
		run = false
		for i := 0; i < m; i++ {
			for j := 0; j < n-2; j++ {
				if board[i][j] != 0 && abs(board[i][j]) == abs(board[i][j+1]) && abs(board[i][j]) == abs(board[i][j+2]) {
					run = true
					t := -abs(board[i][j])
					board[i][j], board[i][j+1], board[i][j+2] = t, t, t
				}
			}
		}
		for j := 0; j < n; j++ {
			for i := 0; i < m-2; i++ {
				if board[i][j] != 0 && abs(board[i][j]) == abs(board[i+1][j]) && abs(board[i][j]) == abs(board[i+2][j]) {
					run = true
					t := -abs(board[i][j])
					board[i][j], board[i+1][j], board[i+2][j] = t, t, t
				}
			}
		}
		if run {
			for j := 0; j < n; j++ {
				curr := m - 1
				for i := m - 1; i >= 0; i-- {
					if board[i][j] > 0 {
						board[curr][j] = board[i][j]
						curr--
					}
				}
				for curr > -1 {
					board[curr][j] = 0
					curr--
				}
			}
		}
	}
	return board
}

func abs(x int) int {
	if x >= 0 {
		return x
	}
	return -x
}