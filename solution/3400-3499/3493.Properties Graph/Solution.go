func numberOfComponents(properties [][]int, k int) (ans int) {
	n := len(properties)
	ss := make([]map[int]struct{}, n)
	g := make([][]int, n)

	for i := 0; i < n; i++ {
		ss[i] = make(map[int]struct{})
		for _, x := range properties[i] {
			ss[i][x] = struct{}{}
		}
	}

	for i := 0; i < n; i++ {
		for j := 0; j < i; j++ {
			cnt := 0
			for x := range ss[i] {
				if _, ok := ss[j][x]; ok {
					cnt++
				}
			}
			if cnt >= k {
				g[i] = append(g[i], j)
				g[j] = append(g[j], i)
			}
		}
	}

	vis := make([]bool, n)
	var dfs func(int)
	dfs = func(i int) {
		vis[i] = true
		for _, j := range g[i] {
			if !vis[j] {
				dfs(j)
			}
		}
	}

	for i := 0; i < n; i++ {
		if !vis[i] {
			dfs(i)
			ans++
		}
	}
	return
}
