var p []int
var size []int

func pondSizes(land [][]int) []int {
	m, n := len(land), len(land[0])
	p = make([]int, m*n)
	size = make([]int, m*n)
	for i := 0; i < m*n; i++ {
		p[i] = i
		size[i] = 1
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if land[i][j] != 0 {
				continue
			}
			idx := i*n + j
			if i < m-1 && land[i+1][j] == 0 {
				union(idx, (i+1)*n+j)
			}
			if j < n-1 && land[i][j+1] == 0 {
				union(idx, i*n+j+1)
			}
			if i < m-1 && j < n-1 && land[i+1][j+1] == 0 {
				union(idx, (i+1)*n+j+1)
			}
			if i < m-1 && j > 0 && land[i+1][j-1] == 0 {
				union(idx, (i+1)*n+j-1)
			}
		}
	}
	s := make(map[int]bool)
	var res []int
	for i := 0; i < m*n; i++ {
		if land[i/n][i%n] != 0 {
			continue
		}
		root := find(i)
		if !s[root] {
			s[root] = true
			res = append(res, size[root])
		}
	}
	sort.Ints(res)
	return res
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}

func union(a, b int) {
	pa, pb := find(a), find(b)
	if pa == pb {
		return
	}
	size[pb] += size[pa]
	p[pa] = pb
}