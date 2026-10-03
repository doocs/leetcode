func minimumFlips(n int, edges [][]int, start string, target string) []int {
	g := make([][]struct{ to, idx int }, n)
	for i := 0; i < n-1; i++ {
		a, b := edges[i][0], edges[i][1]
		g[a] = append(g[a], struct{ to, idx int }{b, i})
		g[b] = append(g[b], struct{ to, idx int }{a, i})
	}
	ans := []int{}
	need := make([]bool, n)
	stk := [][3]int{{0, -1, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		a, fa, state := cur[0], cur[1], cur[2]
		if state == 0 {
			stk = append(stk, [3]int{a, fa, 1})
			for _, p := range g[a] {
				if p.to != fa {
					stk = append(stk, [3]int{p.to, a, 0})
				}
			}
		} else {
			rev := start[a] != target[a]
			for _, p := range g[a] {
				b, i := p.to, p.idx
				if b != fa && need[b] {
					ans = append(ans, i)
					rev = !rev
				}
			}
			need[a] = rev
		}
	}
	if need[0] {
		return []int{-1}
	}
	sort.Ints(ans)
	return ans
}
