func maxCompatibilitySum(students [][]int, mentors [][]int) (ans int) {
	m, n := len(students), len(students[0])
	g := make([][]int, m)
	vis := make([]bool, m)
	for i := range g {
		g[i] = make([]int, m)
		for j := range g {
			for k := 0; k < n; k++ {
				if students[i][k] == mentors[j][k] {
					g[i][j]++
				}
			}
		}
	}
	var dfs func(int, int)
	dfs = func(i, t int) {
		if i == m {
			ans = max(ans, t)
			return
		}
		for j := 0; j < m; j++ {
			if !vis[j] {
				vis[j] = true
				dfs(i+1, t+g[i][j])
				vis[j] = false
			}
		}
	}
	dfs(0, 0)
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}