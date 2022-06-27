func checkMove(board [][]byte, rMove int, cMove int, color byte) bool {
	dirs := [8][2]int{{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}}
	n := 8
	for _, d := range dirs {
		a, b := d[0], d[1]
		i, j := rMove, cMove
		t := 0
		for 0 <= i+a && i+a < n && 0 <= j+b && j+b < n {
			t++
			i += a
			j += b
			if board[i][j] == '.' || board[i][j] == color {
				break
			}
		}
		if board[i][j] == color && t > 1 {
			return true
		}
	}
	return false
}