func longestCycle(edges []int) int {
	vis := make([]bool, len(edges))
	ans := -1
	for i := range edges {
		if vis[i] {
			continue
		}
		j := i
		cycle := []int{}
		for ; j != -1 && !vis[j]; j = edges[j] {
			vis[j] = true
			cycle = append(cycle, j)
		}
		if j == -1 {
			continue
		}
		for k := range cycle {
			if cycle[k] == j {
				ans = max(ans, len(cycle)-k)
				break
			}
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}