func numRookCaptures(board [][]byte) (ans int) {
	dirs := [5]int{-1, 0, 1, 0, -1}
	for i := 0; i < 8; i++ {
		for j := 0; j < 8; j++ {
			if board[i][j] == 'R' {
				for k := 0; k < 4; k++ {
					x, y := i, j
					a, b := dirs[k], dirs[k+1]
					for x+a >= 0 && x+a < 8 && y+b >= 0 && y+b < 8 && board[x+a][y+b] != 'B' {
						x, y = x+a, y+b
						if board[x][y] == 'p' {
							ans++
							break
						}
					}
				}
			}
		}
	}
	return
}