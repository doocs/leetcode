func minDeletionSize(strs []string) int {
	n := len(strs)
	m := len(strs[0])
	st := make([]bool, n-1)
	ans := 0
	for j := 0; j < m; j++ {
		mustDel := false
		for i := 0; i < n-1; i++ {
			if !st[i] && strs[i][j] > strs[i+1][j] {
				mustDel = true
				break
			}
		}
		if mustDel {
			ans++
		} else {
			for i := 0; i < n-1; i++ {
				if !st[i] && strs[i][j] < strs[i+1][j] {
					st[i] = true
				}
			}
		}
	}
	return ans
}
