func countPairs(n int, edges [][]int) int64 {
	vis := make([]bool, n)
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	var arr []int
	var dfs func(int) int
	dfs = func(i int) int {
		res := 1
		vis[i] = true
		for _, j := range g[i] {
			if !vis[j] {
				res += dfs(j)
			}
		}
		return res
	}

	for i := 0; i < n; i++ {
		if !vis[i] {
			arr = append(arr, dfs(i))
		}
	}
	ans := 0
	t := 0
	for _, v := range arr {
		t += v
		ans += v * (n - t)
	}
	return int64(ans)
}