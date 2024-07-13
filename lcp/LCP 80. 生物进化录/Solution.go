func evolutionaryRecord(parents []int) string {
	n := len(parents)
	g := make([][]int, n)
	for i := 1; i < n; i++ {
		g[parents[i]] = append(g[parents[i]], i)
	}

	var dfs func(int) string
	dfs = func(i int) string {
		var t []string
		for _, j := range g[i] {
			t = append(t, dfs(j))
		}
		sort.Strings(t)
		return "0" + strings.Join(t, "") + "1"
	}

	ans := dfs(0)[1:]
	return strings.TrimRight(ans, "1")
}