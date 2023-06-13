func exist(board [][]byte, word string) bool {
	m, n := len(board), len(board[0])
	var dfs func(int, int, int) bool
	dfs = func(i, j, k int) bool {
		if k == len(word)-1 {
			return board[i][j] == word[k]
		}
		if board[i][j] != word[k] {
			return false
		}
		dirs := [5]int{-1, 0, 1, 0, -1}
		c := board[i][j]
		board[i][j] = '0'
		for u := 0; u < 4; u++ {
			x, y := i+dirs[u], j+dirs[u+1]
			if x >= 0 && x < m && y >= 0 && y < n && board[x][y] != '0' && dfs(x, y, k+1) {
				return true
			}
		}
		board[i][j] = c
		return false
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if dfs(i, j, 0) {
				return true
			}
		}
	}
	return false
}