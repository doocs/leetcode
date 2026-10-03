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
	suspicious[k] = true
	stk := []int{k}
	for len(stk) > 0 {
		i := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		for _, j := range g[i] {
			if !suspicious[j] {
				suspicious[j] = true
				stk = append(stk, j)
			}
		}
	}
	for i := 0; i < n; i++ {
		if suspicious[i] || vis[i] {
			continue
		}
		vis[i] = true
		stk = []int{i}
		for len(stk) > 0 {
			u := stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			for _, j := range f[u] {
				if !vis[j] {
					suspicious[j] = false
					vis[j] = true
					stk = append(stk, j)
				}
			}
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
