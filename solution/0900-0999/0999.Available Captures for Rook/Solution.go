func numRookCaptures(board [][]byte) int {
	n := 8

	find := func() []int {
		for i := 0; i < n; i++ {
			for j := 0; j < n; j++ {
				if board[i][j] == 'R' {
					return []int{i, j}
				}
			}
		}
		return []int{}
	}

	pos := find()
	ans := 0
	dirs := [4][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}
	for _, dir := range dirs {
		x, y, a, b := pos[0], pos[1], dir[0], dir[1]
		for x+a >= 0 && x+a < n && y+b >= 0 && y+b < n && board[x+a][y+b] != 'B' {
			x += a
			y += b
			if board[x][y] == 'p' {
				ans++
				break
			}
		}
	}
	return ans
}