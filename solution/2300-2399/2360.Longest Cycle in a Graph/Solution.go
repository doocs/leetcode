func longestCycle(edges []int) int {
	n := len(edges)
	vis := make([]bool, n)
	ans := -1
	for i := range edges {
		if vis[i] {
			continue
		}
		curr := i
		cycle := []int{}
		for curr != -1 && !vis[curr] {
			cycle = append(cycle, curr)
			vis[curr] = true
			curr = edges[curr]
		}
		if curr == -1 {
			continue
		}
		for j, v := range cycle {
			if v == curr {
				ans = max(ans, len(cycle)-j)
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