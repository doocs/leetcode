var p []int

func regionsBySlashes(grid []string) int {
	n := len(grid)
	p = make([]int, n*n*4)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	for i := 0; i < n; i++ {
		row := grid[i]
		for j := 0; j < n; j++ {
			idx := i*n + j
			if i < n-1 {
				p[find(idx*4+2)] = find((idx + n) * 4)
			}
			if j < n-1 {
				p[find(idx*4+1)] = find((idx+1)*4 + 3)
			}
			if row[j] == '/' {
				p[find(idx*4)] = find(idx*4 + 3)
				p[find(idx*4+1)] = find(idx*4 + 2)
			} else if row[j] == '\\' {
				p[find(idx*4)] = find(idx*4 + 1)
				p[find(idx*4+2)] = find(idx*4 + 3)
			} else {
				p[find(idx*4)] = find(idx*4 + 1)
				p[find(idx*4+1)] = find(idx*4 + 2)
				p[find(idx*4+2)] = find(idx*4 + 3)
			}
		}
	}
	s := make(map[int]bool)
	for i := 0; i < len(p); i++ {
		s[find(i)] = true
	}
	return len(s)
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}