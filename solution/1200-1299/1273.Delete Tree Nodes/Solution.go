func deleteTreeNodes(nodes int, parent []int, value []int) int {
	g := make(map[int][]int)
	for i, p := range parent {
		if p != -1 {
			g[p] = append(g[p], i)
		}
	}
	counter := make([]int, nodes)
	for i := range counter {
		counter[i] = 1
	}
	var dfs func(u int)
	dfs = func(u int) {
		if vs, ok := g[u]; ok {
			for _, v := range vs {
				dfs(v)
				value[u] += value[v]
				counter[u] += counter[v]
			}
		}
		if value[u] == 0 {
			counter[u] = 0
		}
	}
	dfs(0)
	return counter[0]
}