func solveNQueens(n int) [][]string {
	res := [][]string{}
	g := make([][]string, n)
	for i := range g {
		g[i] = make([]string, n)
		for j := range g[i] {
			g[i][j] = "."
		}
	}
	col := make([]bool, n)
	dg := make([]bool, 2*n)
	udg := make([]bool, 2*n)
	dfs(0, n, col, dg, udg, g, &res)
	return res
}

func dfs(u, n int, col, dg, udg []bool, g [][]string, res *[][]string) {
	if u == n {
		t := make([]string, n)
		for i := 0; i < n; i++ {
			t[i] = strings.Join(g[i], "")
		}
		*res = append(*res, t)
		return
	}
	for i := 0; i < n; i++ {
		if !col[i] && !dg[u+i] && !udg[n-u+i] {
			g[u][i] = "Q"
			col[i], dg[u+i], udg[n-u+i] = true, true, true
			dfs(u+1, n, col, dg, udg, g, res)
			g[u][i] = "."
			col[i], dg[u+i], udg[n-u+i] = false, false, false
		}
	}
}