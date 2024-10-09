func remainingMethods(n int, k int, invocations [][]int) []int {
	suspicious := make([]bool, n)
	vis := make([]bool, n)
	f := make([][]int, n)
	g := make([][]int, n)

	for _, e := range invocations {
		a, b := e[0], e[1]
		f[a] = append(f[a], b)
		f[b] = append(f[b], a)
		g[a] = append(g[a], b)
	}

	var dfs func(int)
	dfs = func(i int) {
		suspicious[i] = true
		for _, j := range g[i] {
			if !suspicious[j] {
				dfs(j)
			}
		}
	}

	dfs(k)

	var dfs2 func(int)
	dfs2 = func(i int) {
		vis[i] = true
		for _, j := range f[i] {
			if !vis[j] {
				suspicious[j] = false
				dfs2(j)
			}
		}
	}

	for i := 0; i < n; i++ {
		if !suspicious[i] && !vis[i] {
			dfs2(i)
		}
	}

	var ans []int
	for i := 0; i < n; i++ {
		if !suspicious[i] {
			ans = append(ans, i)
		}
	}

	return ans
}
