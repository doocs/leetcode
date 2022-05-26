func checkIfPrerequisite(numCourses int, prerequisites [][]int, queries [][]int) []bool {
	g := make([][]int, numCourses)
	for i := range g {
		g[i] = make([]int, numCourses)
		for j := range g[i] {
			g[i][j] = -1
		}
	}
	for _, e := range prerequisites {
		a, b := e[0], e[1]
		g[a][b] = 1
	}
	var ans []bool
	var dfs func(a, b int) bool
	dfs = func(a, b int) bool {
		if g[a][b] != -1 {
			return g[a][b] == 1
		}
		if a == b {
			g[a][b] = 1
			return true
		}
		for i, c := range g[a] {
			if c == 1 && dfs(i, b) {
				g[a][b] = 1
				return true
			}
		}
		g[a][b] = 0
		return false
	}
	for _, e := range queries {
		a, b := e[0], e[1]
		ans = append(ans, dfs(a, b))
	}
	return ans
}