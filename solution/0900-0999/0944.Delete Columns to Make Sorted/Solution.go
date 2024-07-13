func minDeletionSize(strs []string) (ans int) {
	m, n := len(strs[0]), len(strs)
	for j := 0; j < m; j++ {
		for i := 1; i < n; i++ {
			if strs[i][j] < strs[i-1][j] {
				ans++
				break
			}
		}
	}
	return
}