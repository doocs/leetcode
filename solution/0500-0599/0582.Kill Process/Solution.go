func killProcess(pid []int, ppid []int, kill int) []int {
	g := make(map[int][]int)
	for i, c := range pid {
		p := ppid[i]
		g[p] = append(g[p], c)
	}
	var ans []int
	var dfs func(u int)
	dfs = func(u int) {
		ans = append(ans, u)
		for _, v := range g[u] {
			dfs(v)
		}
	}
	dfs(kill)
	return ans
}