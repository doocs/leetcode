func isBipartite(graph [][]int) bool {
	n := len(graph)
	color := make([]int, n)
	var dfs func(u, c int) bool
	dfs = func(u, c int) bool {
		color[u] = c
		for _, v := range graph[u] {
			if color[v] == 0 {
				if !dfs(v, 3-c) {
					return false
				}
			} else if color[v] == c {
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