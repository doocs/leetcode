func minimumVisitedCells(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	row := make([]int, m)
	col := make([]int, n)
	q := [][3]int{{1, 0, 0}}
	for len(q) > 0 {
		p := q[0]
		dist, i, j := p[0], p[1], p[2]
		if i == m-1 && j == n-1 {
			return dist
		}
		q = q[1:]
		for k := max(row[i], j) + 1; k < min(n, j+grid[i][j]+1); k++ {
			q = append(q, [3]int{dist + 1, i, k})
			row[i] = k
		}
		for k := max(col[j], i) + 1; k < min(m, i+grid[i][j]+1); k++ {
			q = append(q, [3]int{dist + 1, k, j})
			col[j] = k
		}
	}
	return -1
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}