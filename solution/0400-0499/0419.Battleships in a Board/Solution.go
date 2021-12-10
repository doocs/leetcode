func countBattleships(board [][]byte) int {
	m, n := len(board), len(board[0])
	ans := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if board[i][j] == '.' {
				continue
			}
			if i > 0 && board[i-1][j] == 'X' {
				continue
			}
			if j > 0 && board[i][j-1] == 'X' {
				continue
			}
			ans++
		}
	}
	return ans
}