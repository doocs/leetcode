func findLonelyPixel(picture [][]byte) int {
	m, n := len(picture), len(picture[0])
	rows := make([]int, m)
	cols := make([]int, n)
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if picture[i][j] == 'B' {
				rows[i]++
				cols[j]++
			}
		}
	}
	res := 0
	for i := 0; i < m; i++ {
		if rows[i] == 1 {
			for j := 0; j < n; j++ {
				if picture[i][j] == 'B' && cols[j] == 1 {
					res++
					break
				}
			}
		}
	}
	return res
}