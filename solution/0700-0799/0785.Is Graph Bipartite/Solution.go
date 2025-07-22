func isBipartite(graph [][]int) bool {
	n := len(graph)
	color := make([]int, n)
	var dfs func(int, int) bool
	dfs = func(a, c int) bool {
		color[a] = c
		for _, b := range graph[a] {
			if color[b] == c || (color[b] == 0 && !dfs(b, -c)) {
				return false
			}
		}
		return true
	}
	for i := range graph {
		if color[i] == 0 && !dfs(i, 1) {
			return false
		}
	}
	return true
}
