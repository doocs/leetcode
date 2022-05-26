func findBlackPixel(picture [][]byte, target int) int {
	m, n := len(picture), len(picture[0])
	rows := make([]int, m)
	cols := make(map[int][]int)
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if picture[i][j] == 'B' {
				rows[i]++
				cols[j] = append(cols[j], i)
			}
		}
	}
	t := make([][]bool, m)
	for i := 0; i < m; i++ {
		t[i] = make([]bool, m)
	}
	for i := 0; i < m; i++ {
		for k := i; k < m; k++ {
			if i == k {
				t[i][k] = true
			} else {
				t[i][k] = all(picture[i], picture[k])
			}
			t[k][i] = t[i][k]
		}
	}
	res := 0
	for i := 0; i < m; i++ {
		if rows[i] == target {
			for j := 0; j < n; j++ {
				col, ok := cols[j]
				if ok && len(col) == target {
					check := true
					for _, k := range col {
						check = check && t[i][k]
					}
					if check {
						res++
					}
				}
			}
		}
	}
	return res
}

func all(row1, row2 []byte) bool {
	n := len(row1)
	for i := 0; i < n; i++ {
		if row1[i] != row2[i] {
			return false
		}
	}
	return true
}