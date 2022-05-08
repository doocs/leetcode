func minimumMoves(grid [][]int) int {
	n := len(grid)
	target := []int{n*n - 2, n*n - 1}
	q := [][]int{{0, 1}}
	vis := make([][]bool, n*n)
	for i := range vis {
		vis[i] = make([]bool, n*n)
	}
	vis[0][1] = true
	ans := 0
	check := func(a, b int) {
		if !vis[a][b] {
			vis[a][b] = true
			q = append(q, []int{a, b})
		}
	}
	for len(q) > 0 {
		for k := len(q); k > 0; k-- {
			p := q[0]
			q = q[1:]
			if p[0] == target[0] && p[1] == target[1] {
				return ans
			}
			a, b := p[0], p[1]
			i1, j1 := a/n, a%n
			i2, j2 := b/n, b%n
			if j1+1 < n && j2+1 < n && grid[i1][j1+1] == 0 && grid[i2][j2+1] == 0 {
				check(i1*n+j1+1, i2*n+j2+1)
				if j1 == j2 {
					check(a, i1*n+j2+1)
				}
			}
			if i1+1 < n && i2+1 < n && grid[i1+1][j1] == 0 && grid[i2+1][j2] == 0 {
				check((i1+1)*n+j1, (i2+1)*n+j2)
				if i1 == i2 {
					check(a, (i2+1)*n+j1)
				}
			}
		}
		ans++
	}
	return -1
}