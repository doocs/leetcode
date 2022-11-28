func totalNQueens(n int) (ans int) {
	cols := [10]bool{}
	dg := [20]bool{}
	udg := [20]bool{}
	var dfs func(int)
	dfs = func(i int) {
		if i == n {
			ans++
			return
		}
		for j := 0; j < n; j++ {
			a, b := i+j, i-j+n
			if cols[j] || dg[a] || udg[b] {
				continue
			}
			cols[j], dg[a], udg[b] = true, true, true
			dfs(i + 1)
			cols[j], dg[a], udg[b] = false, false, false
		}
	}
	dfs(0)
	return
}