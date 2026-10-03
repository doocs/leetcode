func rootCount(edges [][]int, guesses [][]int, k int) (ans int) {
	n := len(edges) + 1
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	gs := map[int]int{}
	f := func(i, j int) int {
		return i*n + j
	}
	for _, e := range guesses {
		a, b := e[0], e[1]
		gs[f(a, b)]++
	}
	cnt := 0
	type frame struct{ i, fa int }
	stk := []frame{{0, -1}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		for _, j := range g[cur.i] {
			if j != cur.fa {
				cnt += gs[f(cur.i, j)]
				stk = append(stk, frame{j, cur.i})
			}
		}
	}
	type step struct{ i, fa, c int }
	walk := []step{{0, -1, cnt}}
	for len(walk) > 0 {
		cur := walk[len(walk)-1]
		walk = walk[:len(walk)-1]
		if cur.c >= k {
			ans++
		}
		for _, j := range g[cur.i] {
			if j != cur.fa {
				walk = append(walk, step{j, cur.i, cur.c - gs[f(cur.i, j)] + gs[f(j, cur.i)]})
			}
		}
	}
	return
}
