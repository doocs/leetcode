func isPreorder(nodes [][]int) bool {
	k := 0
	g := map[int][]int{}
	for _, node := range nodes {
		g[node[1]] = append(g[node[1]], node[0])
	}
	var dfs func(int) bool
	dfs = func(i int) bool {
		if i != nodes[k][0] {
			return false
		}
		k++
		for _, j := range g[i] {
			if !dfs(j) {
				return false
			}
		}
		return true
	}
	return dfs(nodes[0][0]) && k == len(nodes)
}