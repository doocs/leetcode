func validTicTacToe(board []string) bool {
	var x, o int
	for _, row := range board {
		for _, c := range row {
			if c == 'X' {
				x++
			} else if c == 'O' {
				o++
			}
		}
	}
	win := func(x byte) bool {
		for i := 0; i < 3; i++ {
			if board[i][0] == x && board[i][1] == x && board[i][2] == x {
				return true
			}
			if board[0][i] == x && board[1][i] == x && board[2][i] == x {
				return true
			}
		}
		if board[0][0] == x && board[1][1] == x && board[2][2] == x {
			return true
		}
		return board[0][2] == x && board[1][1] == x && board[2][0] == x
	}
	if x != o && x-1 != o {
		return false
	}
	if win('X') && x-1 != o {
		return false
	}
	return !(win('O') && x != o)
}