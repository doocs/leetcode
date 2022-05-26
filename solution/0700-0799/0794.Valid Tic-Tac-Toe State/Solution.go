func validTicTacToe(board []string) bool {
	x, o := 0, 0
	for i := 0; i < 3; i++ {
		for j := 0; j < 3; j++ {
			if board[i][j] == 'X' {
				x++
			} else if board[i][j] == 'O' {
				o++
			}
		}
	}
	if x != o && x-1 != o {
		return false
	}
	if win(board, 'X') && x-1 != o {
		return false
	}
	return !(win(board, 'O') && x != o)
}

func win(b []string, p byte) bool {
	for i := 0; i < 3; i++ {
		if b[i][0] == p && b[i][1] == p && b[i][2] == p {
			return true
		}
		if b[0][i] == p && b[1][i] == p && b[2][i] == p {
			return true
		}
	}
	if b[0][0] == p && b[1][1] == p && b[2][2] == p {
		return true
	}
	return b[0][2] == p && b[1][1] == p && b[2][0] == p
}
