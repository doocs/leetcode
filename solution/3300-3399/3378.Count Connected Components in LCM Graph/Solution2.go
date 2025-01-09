func dfs(node int, adj [][]int, visited []bool) {
	if visited[node] {
		return
	}
	visited[node] = true
	for _, neighbor := range adj[node] {
		dfs(neighbor, adj, visited)
	}
}

func countComponents(nums []int, threshold int) int {
	adj := make([][]int, threshold+1)
	for i := range adj {
		adj[i] = []int{}
	}

	visited := make([]bool, threshold+1)
	components := 0

	for _, num := range nums {
		if num > threshold {
			components++
			continue
		}
		for j := 2 * num; j <= threshold; j += num {
			adj[num] = append(adj[num], j)
			adj[j] = append(adj[j], num)
		}
	}

	for _, num := range nums {
		if num <= threshold && !visited[num] {
			dfs(num, adj, visited)
			components++
		}
	}

	return components
}
