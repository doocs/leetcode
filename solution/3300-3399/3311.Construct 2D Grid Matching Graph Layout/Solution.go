func constructGridLayout(n int, edges [][]int) [][]int {
	g := make([][]int, n)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}

	deg := make([]int, 5)
	for i := range deg {
		deg[i] = -1
	}

	for x := 0; x < n; x++ {
		deg[len(g[x])] = x
	}

	var row []int
	if deg[1] != -1 {
		row = append(row, deg[1])
	} else if deg[4] == -1 {
		x := deg[2]
		for _, y := range g[x] {
			if len(g[y]) == 2 {
				row = append(row, x, y)
				break
			}
		}
	} else {
		x := deg[2]
		row = append(row, x)
		pre := x
		x = g[x][0]
		for len(g[x]) > 2 {
			row = append(row, x)
			for _, y := range g[x] {
				if y != pre && len(g[y]) < 4 {
					pre = x
					x = y
					break
				}
			}
		}
		row = append(row, x)
	}

	ans := [][]int{row}
	vis := make([]bool, n)
	rowSize := len(row)
	for i := 0; i < n/rowSize-1; i++ {
		for _, x := range row {
			vis[x] = true
		}
		nxt := []int{}
		for _, x := range row {
			for _, y := range g[x] {
				if !vis[y] {
					nxt = append(nxt, y)
					break
				}
			}
		}
		ans = append(ans, nxt)
		row = nxt
	}

	return ans
}
