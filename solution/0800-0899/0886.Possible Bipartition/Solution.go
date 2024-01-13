func possibleBipartition(n int, dislikes [][]int) bool {
	g := make([][]int, n)
	for _, e := range dislikes {
		a, b := e[0]-1, e[1]-1
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	color := make([]int, n)
	var dfs func(int, int) bool
	dfs = func(i, c int) bool {
		color[i] = c
		for _, j := range g[i] {
			if color[j] == c {
				return false
			}
			if color[j] == 0 && !dfs(j, 3-c) {
				return false
			}
		}
		return true
	}
	for i, c := range color {
		if c == 0 && !dfs(i, 1) {
			return false
		}
	}
	return true
}