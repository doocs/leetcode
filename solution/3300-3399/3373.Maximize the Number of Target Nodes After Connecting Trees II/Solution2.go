func maxTargetNodes(edges1 [][]int, edges2 [][]int) []int {
	g1 := build(edges1)
	g2 := build(edges2)
	n, m := len(g1), len(g2)
	c1 := make([]int, n)
	c2 := make([]int, m)
	cnt1 := make([]int, 2)
	cnt2 := make([]int, 2)

	dfs(g2, c2, cnt2)
	dfs(g1, c1, cnt1)

	t := max(cnt2[0], cnt2[1])
	ans := make([]int, n)
	for i := 0; i < n; i++ {
		ans[i] = t + cnt1[c1[i]]
	}
	return ans
}

func build(edges [][]int) [][]int {
	n := len(edges) + 1
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	return g
}

func dfs(g [][]int, c []int, cnt []int) {
	stk := [][3]int{{0, -1, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		a, fa, d := cur[0], cur[1], cur[2]
		c[a] = d
		cnt[d]++
		for _, b := range g[a] {
			if b != fa {
				stk = append(stk, [3]int{b, a, d ^ 1})
			}
		}
	}
}
