func magnificentSets(n int, edges [][]int) int {
	g := make([][]int, n+1)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	arr := []int{}
	vis := make([]bool, n+1)
	ans := 0
	var dfs func(int)
	dfs = func(i int) {
		arr = append(arr, i)
		vis[i] = true
		for _, j := range g[i] {
			if !vis[j] {
				dfs(j)
			}
		}
	}
	bfs := func(k int) int {
		ans := 1
		dist := make([]int, n+1)
		for i := range dist {
			dist[i] = 1 << 30
		}
		q := []int{k}
		dist[k] = 1
		for len(q) > 0 {
			i := q[0]
			q = q[1:]
			for _, j := range g[i] {
				if dist[j] == 1<<30 {
					dist[j] = dist[i] + 1
					ans = dist[j]
					q = append(q, j)
				}
			}
		}
		for _, i := range arr {
			if dist[i] == 1<<30 {
				ans++
				dist[i] = ans
			}
		}
		for _, i := range arr {
			for _, j := range g[i] {
				if abs(dist[i]-dist[j]) != 1 {
					return -1
				}
			}
		}
		return ans
	}
	for i := 1; i <= n; i++ {
		if !vis[i] {
			dfs(i)
			t := -1
			for _, v := range arr {
				t = max(t, bfs(v))
			}
			if t == -1 {
				return -1
			}
			ans += t
			arr = []int{}
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

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}