func longestPath(parent []int, s string) int {
	n := len(parent)
	g := make([][]int, n)
	for i := 1; i < n; i++ {
		g[parent[i]] = append(g[parent[i]], i)
	}
	down := make([]int, n)
	ans := 0
	stk := [][2]int{{0, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		i, state := cur[0], cur[1]
		if state == 0 {
			stk = append(stk, [2]int{i, 1})
			for _, j := range g[i] {
				stk = append(stk, [2]int{j, 0})
			}
		} else {
			mx := 0
			for _, j := range g[i] {
				x := down[j] + 1
				if s[i] != s[j] {
					ans = max(ans, x+mx)
					mx = max(mx, x)
				}
			}
			down[i] = mx
		}
	}
	return ans + 1
}
