func restoreArray(adjacentPairs [][]int) []int {
	graph := make(map[int][]int)
	for _, pair := range adjacentPairs {
		graph[pair[0]] = append(graph[pair[0]], pair[1])
		graph[pair[1]] = append(graph[pair[1]], pair[0])
	}
	ans := make([]int, 0)
	vis := make(map[int]bool)
	var start int
	for idx, adj := range graph {
		if len(adj) == 1 {
			start = idx
			break
		}
	}
	dfs(graph, &ans, vis, start)
	return ans
}

func dfs(graph map[int][]int, ans *[]int, vis map[int]bool, idx int) {
	if vis[idx] {
		return
	}
	vis[idx] = true
	*ans = append(*ans, idx)
	for _, next := range graph[idx] {
		dfs(graph, ans, vis, next)
	}
}
