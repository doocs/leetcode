func mostProfitablePath(edges [][]int, bob int, amount []int) int {
	n := len(edges) + 1
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	parent := make([]int, n)
	for i := range parent {
		parent[i] = -1
	}
	seen := make([]bool, n)
	seen[0] = true
	stk := []int{0}
	for len(stk) > 0 {
		i := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		for _, j := range g[i] {
			if !seen[j] {
				seen[j] = true
				parent[j] = i
				stk = append(stk, j)
			}
		}
	}
	ts := make([]int, n)
	for i := range ts {
		ts[i] = n
	}
	for x, t := bob, 0; x != -1; x, t = parent[x], t+1 {
		ts[x] = t
	}
	ans := -0x3f3f3f3f
	type frame struct{ i, fa, t, v int }
	walk := []frame{{0, -1, 0, 0}}
	for len(walk) > 0 {
		f := walk[len(walk)-1]
		walk = walk[:len(walk)-1]
		v := f.v
		if f.t == ts[f.i] {
			v += amount[f.i] >> 1
		} else if f.t < ts[f.i] {
			v += amount[f.i]
		}
		if len(g[f.i]) == 1 && g[f.i][0] == f.fa {
			ans = max(ans, v)
			continue
		}
		for _, j := range g[f.i] {
			if j != f.fa {
				walk = append(walk, frame{j, f.i, f.t + 1, v})
			}
		}
	}
	return ans
}
