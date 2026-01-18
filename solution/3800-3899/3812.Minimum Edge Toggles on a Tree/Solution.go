func minimumFlips(n int, edges [][]int, start string, target string) []int {
	g := make([][]struct{ to, idx int }, n)
	for i := 0; i < n-1; i++ {
		a, b := edges[i][0], edges[i][1]
		g[a] = append(g[a], struct{ to, idx int }{b, i})
		g[b] = append(g[b], struct{ to, idx int }{a, i})
	}
	ans := []int{}
	var dfs func(a, fa int) bool
	dfs = func(a, fa int) bool {
		rev := start[a] != target[a]
		for _, p := range g[a] {
			b, i := p.to, p.idx
			if b != fa && dfs(b, a) {
				ans = append(ans, i)
				rev = !rev
			}
		}
		return rev
	}
	if dfs(0, -1) {
		return []int{-1}
	}
	sort.Ints(ans)
	return ans
}
