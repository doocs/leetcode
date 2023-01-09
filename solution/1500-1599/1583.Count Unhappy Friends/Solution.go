func unhappyFriends(n int, preferences [][]int, pairs [][]int) (ans int) {
	d := make([][]int, n)
	p := make([]int, n)
	for i := range d {
		d[i] = make([]int, n)
		for j := 0; j < n-1; j++ {
			d[i][preferences[i][j]] = j
		}
	}
	for _, e := range pairs {
		x, y := e[0], e[1]
		p[x] = y
		p[y] = x
	}
	for x := 0; x < n; x++ {
		y := p[x]
		find := 0
		for i := 0; i < d[x][y]; i++ {
			u := preferences[x][i]
			if d[u][x] < d[u][p[u]] {
				find = 1
				break
			}
		}
		ans += find
	}
	return
}