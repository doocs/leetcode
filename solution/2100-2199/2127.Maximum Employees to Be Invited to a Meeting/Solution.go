func maximumInvitations(favorite []int) int {
	a, b := maxCycle(favorite), topologicalSort(favorite)
	return max(a, b)
}

func maxCycle(fa []int) int {
	n := len(fa)
	vis := make([]bool, n)
	ans := 0
	for i := range fa {
		if vis[i] {
			continue
		}
		j := i
		cycle := []int{}
		for !vis[j] {
			cycle = append(cycle, j)
			vis[j] = true
			j = fa[j]
		}
		for k, v := range cycle {
			if v == j {
				ans = max(ans, len(cycle)-k)
				break
			}
		}
	}
	return ans
}

func topologicalSort(fa []int) int {
	n := len(fa)
	indeg := make([]int, n)
	dist := make([]int, n)
	for i := range fa {
		dist[i] = 1
	}
	for _, v := range fa {
		indeg[v]++
	}
	q := []int{}
	for i, v := range indeg {
		if v == 0 {
			q = append(q, i)
		}
	}
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		dist[fa[i]] = max(dist[fa[i]], dist[i]+1)
		indeg[fa[i]]--
		if indeg[fa[i]] == 0 {
			q = append(q, fa[i])
		}
	}
	ans := 0
	for i := range fa {
		if i == fa[fa[i]] {
			ans += dist[i]
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