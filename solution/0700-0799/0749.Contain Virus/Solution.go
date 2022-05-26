func containVirus(isInfected [][]int) int {
	m, n := len(isInfected), len(isInfected[0])
	ans := 0
	dirs := []int{-1, 0, 1, 0, -1}
	max := func(boundaries []map[int]bool) int {
		idx := 0
		mx := len(boundaries[0])
		for i, v := range boundaries {
			t := len(v)
			if mx < t {
				mx = t
				idx = i
			}
		}
		return idx
	}

	for {
		vis := make([][]bool, m)
		for i := range vis {
			vis[i] = make([]bool, n)
		}
		areas := []map[int]bool{}
		boundaries := []map[int]bool{}
		c := []int{}

		var dfs func(i, j int)
		dfs = func(i, j int) {
			vis[i][j] = true
			idx := len(areas) - 1
			areas[idx][i*n+j] = true
			for k := 0; k < 4; k++ {
				x, y := i+dirs[k], j+dirs[k+1]
				if x >= 0 && x < m && y >= 0 && y < n {
					if isInfected[x][y] == 1 && !vis[x][y] {
						dfs(x, y)
					} else if isInfected[x][y] == 0 {
						c[idx]++
						boundaries[idx][x*n+y] = true
					}
				}
			}
		}

		for i, row := range isInfected {
			for j, v := range row {
				if v == 1 && !vis[i][j] {
					areas = append(areas, map[int]bool{})
					boundaries = append(boundaries, map[int]bool{})
					c = append(c, 0)
					dfs(i, j)
				}
			}
		}
		if len(areas) == 0 {
			break
		}
		idx := max(boundaries)
		ans += c[idx]
		for t, area := range areas {
			if t == idx {
				for v := range area {
					i, j := v/n, v%n
					isInfected[i][j] = -1
				}
			} else {
				for v := range area {
					i, j := v/n, v%n
					for k := 0; k < 4; k++ {
						x, y := i+dirs[k], j+dirs[k+1]
						if x >= 0 && x < m && y >= 0 && y < n && isInfected[x][y] == 0 {
							isInfected[x][y] = 1
						}
					}
				}
			}
		}

	}
	return ans
}