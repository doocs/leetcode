func sumOfDistancesInTree(n int, edges [][]int) []int {
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	ans := make([]int, n)
	size := make([]int, n)
	type frame struct{ i, fa, d, state int }
	stk := []frame{{0, -1, 0, 0}}
	for len(stk) > 0 {
		f := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		if f.state == 0 {
			ans[0] += f.d
			stk = append(stk, frame{f.i, f.fa, f.d, 1})
			for _, j := range g[f.i] {
				if j != f.fa {
					stk = append(stk, frame{j, f.i, f.d + 1, 0})
				}
			}
		} else {
			size[f.i] = 1
			for _, j := range g[f.i] {
				if j != f.fa {
					size[f.i] += size[j]
				}
			}
		}
	}
	type step struct{ i, fa, t int }
	walk := []step{{0, -1, ans[0]}}
	for len(walk) > 0 {
		f := walk[len(walk)-1]
		walk = walk[:len(walk)-1]
		ans[f.i] = f.t
		for _, j := range g[f.i] {
			if j != f.fa {
				walk = append(walk, step{j, f.i, f.t - size[j] + n - size[j]})
			}
		}
	}
	return ans
}
