func placeWordInCrossword(board [][]byte, word string) bool {
	m, n := len(board), len(board[0])
	k := len(word)
	check := func(i, j, a, b int) bool {
		x, y := i+a*k, j+b*k
		if x >= 0 && x < m && y >= 0 && y < n && board[x][y] != '#' {
			return false
		}
		for _, c := range word {
			if i < 0 || i >= m || j < 0 || j >= n || (board[i][j] != ' ' && board[i][j] != byte(c)) {
				return false
			}
			i, j = i+a, j+b
		}
		return true
	}
	for i := range board {
		for j := range board[i] {
			leftToRight := (j == 0 || board[i][j-1] == '#') && check(i, j, 0, 1)
			rightToLeft := (j == n-1 || board[i][j+1] == '#') && check(i, j, 0, -1)
			upToDown := (i == 0 || board[i-1][j] == '#') && check(i, j, 1, 0)
			downToUp := (i == m-1 || board[i+1][j] == '#') && check(i, j, -1, 0)
			if leftToRight || rightToLeft || upToDown || downToUp {
				return true
			}
		}
	}
	return false
}