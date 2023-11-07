func printKMoves(K int) []string {
	var x1, y1, x2, y2, x, y, p int
	dirs := [5]int{0, 1, 0, -1, 0}
	d := "RDLU"
	type pair struct{ x, y int }
	black := map[pair]bool{}
	for K > 0 {
		t := pair{x, y}
		if black[t] {
			delete(black, t)
			p = (p + 3) % 4
		} else {
			black[t] = true
			p = (p + 1) % 4
		}
		x += dirs[p]
		y += dirs[p+1]
		x1 = min(x1, x)
		y1 = min(y1, y)
		x2 = max(x2, x)
		y2 = max(y2, y)
		K--
	}
	m, n := x2-x1+1, y2-y1+1
	g := make([][]byte, m)
	for i := range g {
		g[i] = make([]byte, n)
		for j := range g[i] {
			g[i][j] = '_'
		}
	}
	for t := range black {
		i, j := t.x-x1, t.y-y1
		g[i][j] = 'X'
	}
	g[x-x1][y-y1] = d[p]
	ans := make([]string, m)
	for i := range ans {
		ans[i] = string(g[i])
	}
	return ans
}