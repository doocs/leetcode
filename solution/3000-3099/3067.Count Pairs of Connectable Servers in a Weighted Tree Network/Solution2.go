func countPairsOfConnectableServers(edges [][]int, signalSpeed int) []int {
	n := len(edges) + 1
	type pair struct{ x, w int }
	g := make([][]pair, n)
	for _, e := range edges {
		a, b, w := e[0], e[1], e[2]
		g[a] = append(g[a], pair{b, w})
		g[b] = append(g[b], pair{a, w})
	}
	count := func(start, fa, dist int) int {
		cnt := 0
		stk := [][3]int{{start, fa, dist}}
		for len(stk) > 0 {
			cur := stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			a, parent, ws := cur[0], cur[1], cur[2]
			if ws%signalSpeed == 0 {
				cnt++
			}
			for _, e := range g[a] {
				b, w := e.x, e.w
				if b != parent {
					stk = append(stk, [3]int{b, a, ws + w})
				}
			}
		}
		return cnt
	}
	ans := make([]int, n)
	for a := 0; a < n; a++ {
		s := 0
		for _, e := range g[a] {
			t := count(e.x, a, e.w)
			ans[a] += s * t
			s += t
		}
	}
	return ans
}
