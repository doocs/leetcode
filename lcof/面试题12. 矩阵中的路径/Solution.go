func exist(board [][]byte, word string) bool {
	m, n := len(board), len(board[0])
	var dfs func(i, j, k int) bool
	dfs = func(i, j, k int) bool {
		if k == len(word) {
			return true
		}
		if i < 0 || i >= m || j < 0 || j >= n || board[i][j] != word[k] {
			return false
		}
		board[i][j] = ' '
		dirs := []int{-1, 0, 1, 0, -1}
		ans := false
		for l := 0; l < 4; l++ {
			ans = ans || dfs(i+dirs[l], j+dirs[l+1], k+1)
		}
		board[i][j] = word[k]
		return ans
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