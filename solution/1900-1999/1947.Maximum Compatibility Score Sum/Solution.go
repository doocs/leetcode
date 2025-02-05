func maxCompatibilitySum(students [][]int, mentors [][]int) (ans int) {
	m, n := len(students), len(students[0])
	g := make([][]int, m)
	vis := make([]bool, m)
	for i, x := range students {
		g[i] = make([]int, m)
		for j, y := range mentors {
			for k := 0; k < n; k++ {
				if x[k] == y[k] {
					g[i][j]++
				}
			}
		}
	}
	var dfs func(int, int)
	dfs = func(i, s int) {
		if i == m {
			ans = max(ans, s)
			return
		}
		for j := 0; j < m; j++ {
			if !vis[j] {
				vis[j] = true
				dfs(i+1, s+g[i][j])
				vis[j] = false
			}
		}
	}
	dfs(0, 0)
	return
}
