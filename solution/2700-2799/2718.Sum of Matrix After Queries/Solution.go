func matrixSumQueries(n int, queries [][]int) (ans int64) {
	row, col := map[int]bool{}, map[int]bool{}
	m := len(queries)
	for k := m - 1; k >= 0; k-- {
		t, i, v := queries[k][0], queries[k][1], queries[k][2]
		if t == 0 {
			if !row[i] {
				ans += int64(v * (n - len(col)))
				row[i] = true
			}
		} else {
			if !col[i] {
				ans += int64(v * (n - len(row)))
				col[i] = true
			}
		}
	}
	return
}