func minRunesToAdd(n int, crystals []int, flowFrom []int, flowTo []int) (ans int) {
	g := make([][]int, n)
	for i := 0; i < len(flowFrom); i++ {
		a, b := flowFrom[i], flowTo[i]
		g[a] = append(g[a], b)
	}

	vis := make([]int, n)
	for _, x := range crystals {
		vis[x] = 1
	}

	bfs := func(q []int) {
		for len(q) > 0 {
			a := q[0]
			q = q[1:]
			for _, b := range g[a] {
				if vis[b] == 1 {
					continue
				}
				vis[b] = 1
				q = append(q, b)
			}
		}
	}

	seq := []int{}
	var dfs func(a int)
	dfs = func(a int) {
		vis[a] = 2
		for _, b := range g[a] {
			if vis[b] > 0 {
				continue
			}
			dfs(b)
		}
		seq = append(seq, a)
	}

	q := crystals
	bfs(q)

	for i := 0; i < n; i++ {
		if vis[i] == 0 {
			dfs(i)
		}
	}

	for i, j := 0, len(seq)-1; i < j; i, j = i+1, j-1 {
		seq[i], seq[j] = seq[j], seq[i]
	}
	for _, i := range seq {
		if vis[i] == 2 {
			q = []int{i}
			vis[i] = 1
			bfs(q)
			ans++
		}
	}
	return
}
