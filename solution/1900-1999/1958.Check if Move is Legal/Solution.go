func checkMove(board [][]byte, rMove int, cMove int, color byte) bool {
	for a := -1; a <= 1; a++ {
		for b := -1; b <= 1; b++ {
			if a == 0 && b == 0 {
				continue
			}
			i, j := rMove, cMove
			cnt := 0
			for 0 <= i+a && i+a < 8 && 0 <= j+b && j+b < 8 {
				i += a
				j += b
				cnt++
				if cnt > 1 && board[i][j] == color {
					return true
				}
				if board[i][j] == color || board[i][j] == '.' {
					break
				}
			}
		}
	}
	return false
}
